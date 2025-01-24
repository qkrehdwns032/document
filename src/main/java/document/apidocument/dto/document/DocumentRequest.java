package document.apidocument.dto.document;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DocumentRequest {
    private LocalDateTime date;
    private Long projectId;
    private List<EndpointRequest> endpoints;
}
