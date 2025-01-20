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

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Transactional
    public Project createProject(ProjectRequest projectRequest) {
        Project project = new Project();
        project.setProjectName(projectRequest.getProjectName());
        project.setDescription(projectRequest.getDescription());
        project.setIsPrivate(projectRequest.getIsPrivate());

        // user와 parameter의 연관관계 형성해주어야함
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));




        return projectRepository.save(project);
    }

}
