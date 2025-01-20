package document.apidocument.controller;

import document.apidocument.domain.Project;
import document.apidocument.dto.project.ProjectRequest;
import document.apidocument.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create") // project 생성
    public ResponseEntity<Project> createProject(@RequestBody ProjectRequest projectRequest){
        return ResponseEntity.ok(projectService.createProject(projectRequest));
    }

    @PostMapping("/invite")// project에 user 초대
    public void inviteUser(){


    }

    @PostMapping("/update") // project 수정
    public void updateProject(){


    }

    @PostMapping("/delete") // project 삭제
    public void deleteProject(){


    }

    @GetMapping("read") // project 조회
    public void readProject(){


    }


}
