package document.apidocument.controller;

import document.apidocument.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class Project {

    private final ProjectService projectService;

    @PostMapping("/create") // project 생성
    public void createProject(){


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
