package document.apidocument.service;

import document.apidocument.domain.Project;
import document.apidocument.domain.User;
import document.apidocument.dto.project.ProjectRequest;
import document.apidocument.repository.ProjectRepository;
import document.apidocument.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Transactional
    public Project createProject(ProjectRequest projectRequest) {
            Project project = Project.builder()
                    .projectName(projectRequest.getProjectName())
                    .description(projectRequest.getDescription())
                    .isPrivate(projectRequest.getIsPrivate())
                    .build();

            User user = getCurrentUser();
            addUserToProject(project,user);

            return projectRepository.save(project);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void addUserToProject(Project project, User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        project.setUsers(users);
    }

    @Transactional
    public Project readProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return project;
    }

    public Boolean isProjectCreator(Long projectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String loginUserName = authentication.getName(); // 로그인한 사용자와 프로젝트의 생성자가 동일한 지 비교
        Project project = readProject(projectId);
        String projectCreator = project.getProjectCreator();

        if(loginUserName.equals(projectCreator)) {
            return true;
        }
        else{
            return false;
        }
    }

    public void inviteUser(Long projectId, String username) {
        Project project = readProject(projectId);
        List<User> users = project.getUsers();

        User user = userService.findUserByUsername(username);
        users.add(user);

        project.setUsers(users);

        projectRepository.save(project);
    }
}
