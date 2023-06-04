package pl.app.core.security.auth;

import org.springframework.security.core.Authentication;
import pl.app.core.security.auditing.AuthenticationProvider;

import java.util.Optional;

public interface AuthenticationService extends AuthenticationProvider {
    void setCurrentAuthentication(Authentication authentication);

    Authentication getCurrentAuthentication();

    Authentication authenticate(String email, String password);

    Optional<String> getCurrentUserName();
    Optional<Long> getCurrentUserId();
}
