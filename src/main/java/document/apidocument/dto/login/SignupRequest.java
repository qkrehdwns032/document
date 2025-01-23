package document.apidocument.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String username;
    private String loginId;
    private String password;
}
