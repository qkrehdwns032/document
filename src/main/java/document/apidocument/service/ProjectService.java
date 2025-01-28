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

    public List<Project> readAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return projects;
    }

    @Transactional
    public Project createProject(ProjectRequest projectRequest) {
            Project project = Project.builder()
                    .projectName(projectRequest.getProjectName())
                    .description(projectRequest.getDescription())
                    .isPrivate(projectRequest.getIsPrivate())
                    .build();

            User user = getCurrentUser();
            addCreatorToProject(project,user);

            project = projectRepository.save(project);

            addUserToProject(project, user);

            return project;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void addCreatorToProject(Project project, User user) {
        String creatorName = user.getUsername();
        project.setProjectCreator(creatorName);
    }

    @Transactional
    public void addUserToProject(Project project, User user) {
        if (project.getUsers() == null) {
            project.setUsers(new ArrayList<>());
        }
        project.getUsers().add(user);

        if (user.getProjects() == null) {
            user.setProjects(new ArrayList<>());
        }
        user.getProjects().add(project);
    }

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

    @Transactional
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public List<Project> readUserProject(){
        User user = getCurrentUser();

        List<Project> projects = projectRepository.findByUsersContaining(user);

        return projects;
    }

}
