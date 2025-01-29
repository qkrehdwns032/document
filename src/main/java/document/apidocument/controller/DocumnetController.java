package document.apidocument.controller;

import document.apidocument.domain.Document;
import document.apidocument.dto.document.DocumentRequest;
import document.apidocument.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/document/")
public class DocumnetController {

    private final DocumentService documentService;

    // 문서 작성
    @PostMapping("/create")
    public ResponseEntity<Document> createDocument(@RequestBody DocumentRequest documentRequest){
        return ResponseEntity.ok(documentService.createDocument(documentRequest));
    }

    // 문서 조회
    @GetMapping("/read/{documentId}")
    public ResponseEntity<Document> readDocument(@PathVariable Long  documentId){
        Document document = documentService.readDocument(documentId);

        return ResponseEntity.ok(document);
    }

    // 문서 전체 조회
    @GetMapping("/read/all")
    public ResponseEntity<List<Document>> readAllDocuments(){
        List<Document> documents = documentService.readAllDocuments();

        return ResponseEntity.ok(documents);
    }

    //이전의 project데이터 불러오기
    @GetMapping("read/exDocumentData")
    public ResponseEntity<Document> readPreviousDocument(){
        return ResponseEntity.ok(documentService.getPreviousDocument());
    }

}
