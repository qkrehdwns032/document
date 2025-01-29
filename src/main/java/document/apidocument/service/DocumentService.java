package document.apidocument.service;

import document.apidocument.domain.Document;
import document.apidocument.domain.Endpoint;
import document.apidocument.domain.Parameter;
import document.apidocument.domain.Project;
import document.apidocument.dto.document.DocumentRequest;
import document.apidocument.repository.DocumentRepository;
import document.apidocument.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Document createDocument(DocumentRequest dto) {
        // 1. Document 엔티티 생성
        Document document = new Document();
        document.setDate(dto.getDate());

        // Project 설정
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        document.setProject(project);

        // 2. Endpoint 엔티티들 생성 및 연관관계 설정
        List<Endpoint> endpoints = dto.getEndpoints().stream()
                .map(endpointDTO -> {
                    Endpoint endpoint = new Endpoint();
                    endpoint.setPath(endpointDTO.getPath()); // dto 이름이 맞나?
                    endpoint.setMethod(endpointDTO.getMethod());
                    endpoint.setDocument(document);

                    // 3. Parameter 엔티티들 생성 및 연관관계 설정 (수정된 부분)
                    if (endpointDTO.getParameters() != null) {
                        List<Parameter> parameters = endpointDTO.getParameters().stream()
                                .map(paramDTO -> {
                                    Parameter parameter = new Parameter();
                                    parameter.setAnnotation(paramDTO.getAnnotation());
                                    parameter.setType(paramDTO.getType());
                                    parameter.setData(paramDTO.getData());
                                    parameter.setEndpoint(endpoint);
                                    return parameter;
                                })
                                .collect(Collectors.toList());
                        endpoint.setParameters(parameters);
                    }
                    return endpoint;
                })
                .collect(Collectors.toList());

        document.setEndpoints(endpoints);

        // 4. Document 저장 (cascade로 인해 endpoint와 parameter도 함께 저장됨)
        return documentRepository.save(document);
    }

    public Document readDocument(Long documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public List<Document> readAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getPreviousDocument(){
        return documentRepository.findFirstByOrderByCreatedAtDesc()
            .orElseThrow(() -> new NoSuchElementException("이전 문서가 없습니다."));
    }
}
