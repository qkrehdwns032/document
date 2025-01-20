package document.apidocument.dto.document;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EndpointDto {
    private String path;
    private String method;
    private List<ParameterDto> parameters;
}
