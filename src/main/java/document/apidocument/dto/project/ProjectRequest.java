package document.apidocument.dto.project;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProjectRequest {
    private String projectName;
    private String description;
    private LocalDateTime date;
    private Boolean isPrivate;
}
