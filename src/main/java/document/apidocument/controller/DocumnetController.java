package document.apidocument.controller;

import document.apidocument.domain.Document;
import document.apidocument.dto.document.DocumentRequest;
import document.apidocument.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/document/")
public class DocumnetController {

    private final DocumentService documentService;

    // 글 작성
    @PostMapping("/create")
    public ResponseEntity<Document> createDocument(@RequestBody DocumentRequest documentRequest){
        return ResponseEntity.ok(documentService.createDocument(documentRequest));
    }

    // 글 수정

    // 삭제기능은 없이할까

    // 문서에 user collaborator 초대 기능


}
