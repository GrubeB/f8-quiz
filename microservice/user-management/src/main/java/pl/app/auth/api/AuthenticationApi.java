package pl.app.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.app.auth.dto.AuthenticationDto;
import pl.app.core.security.auth.AuthenticationService;
import pl.app.core.security.token.TokenService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationApi {
    private final AuthenticationService service;
    private final TokenService tokenService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationDto dto) {
        Authentication authenticate = service.authenticate(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(tokenService.generateToken(authenticate));
    }
}
