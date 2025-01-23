package document.apidocument.controller;

import document.apidocument.dto.login.LoginRequest;
import document.apidocument.dto.login.SignupRequest;
import document.apidocument.dto.login.TokenResponse;
import document.apidocument.security.jwt.JwtTokenProvider;
import document.apidocument.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLoginId(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(
                authentication.getName(),
                authentication.getAuthorities()
        );

        return ResponseEntity.ok(new TokenResponse(jwt));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // 클라이언트는 저장된 JWT 토큰을 삭제하면 됨
        return ResponseEntity.ok()
                .header("Set-Cookie", "token=; Max-Age=0; Path=/")
                .body("로그아웃 성공"); // 프론트가 cookie에 저장하는 방식이라면 이렇게함. localstorage에 저장하는 방식이라면 그냥 .body()만 작성하면 됨
    }

}