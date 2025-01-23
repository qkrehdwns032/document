package document.apidocument.controller;

import document.apidocument.domain.Project;
import document.apidocument.domain.User;
import document.apidocument.dto.project.ProjectRequest;
import document.apidocument.repository.UserRepository;
import document.apidocument.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserRepository userRepository;

    @PostMapping("/create") // project 생성
    public ResponseEntity<Project> createProject(@RequestBody ProjectRequest projectRequest){
        return ResponseEntity.ok(projectService.createProject(projectRequest));
    }

    @PostMapping("/read/{projectId}/invitations")// project에 user 초대
    public ResponseEntity<String> inviteUser(@PathVariable Long projectId){ // 권한이 있어야 하는가? 초대 버튼을 누르는 컨트롤러인데 초대는 프로젝트를 생성한 사람만 초대할 수 있음.
        Boolean flag = projectService.isProjectCreator(projectId);

        if(flag){ // 초대 권한이 있는 경우
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("초대 가능");
        }
        else{ // 초대 권한이 없는 경우
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한 없음");
        }
    }

    @PostMapping("/read/{projectId}/invitations/send")
    public ResponseEntity<String> sendInvitation(@PathVariable Long projectId, @RequestBody String username){
        try {
            projectService.inviteUser(projectId,username);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("초대 완료");
        }
        catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user를 찾을 수 없음");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버오류");
        }
    }

    @PostMapping("/update") // project 수정
    public void updateProject(){


    }

    @PostMapping("/delete") // project 삭제
    public void deleteProject(){


    }

    @GetMapping("read/{projectId}") // project 조회
    public Project readProject(@PathVariable Long projectId){
        Project project = projectService.readProject(projectId);

        return project;
    }


}
