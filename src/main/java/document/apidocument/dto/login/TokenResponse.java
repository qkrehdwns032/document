package document.apidocument.dto.login;

import lombok.Getter;
import lombok.Setter;

// TokenResponse.java
@Getter
@Setter
public class TokenResponse {
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}