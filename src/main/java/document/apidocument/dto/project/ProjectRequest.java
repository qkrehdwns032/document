package document.apidocument.dto.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {

    private String projectName;
    private String description;
    private Boolean isPrivate;
}
