package document.apidocument.dto.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterDto {
    private String annotation;
    private String type;
    private String data;
}
