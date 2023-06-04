package pl.app.core.security.auth;

import org.springframework.security.core.Authentication;
import pl.app.core.security.auditing.AuthenticationProvider;

import java.util.Optional;

public interface AuthenticationService extends AuthenticationProvider {
    void setCurrentAuthentication(Authentication authentication);

    Authentication authenticate(String email, String password);

    Authentication getCurrentAuthentication();

    Optional<String> getCurrentUserName();
    Optional<Long> getCurrentUserId();

    Optional<String> getAccessToken();
}
