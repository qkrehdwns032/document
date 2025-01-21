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

}
