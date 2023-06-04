package pl.app.core.security.auditing;

import java.util.Optional;

public interface AuthenticationProvider {
    Optional<String> getCurrentUserName();
}
