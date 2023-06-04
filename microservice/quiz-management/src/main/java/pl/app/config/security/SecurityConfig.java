package pl.app.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.app.core.security.auditing.AuditorConfig;
import pl.app.core.security.auth.AuthenticationConfig;
import pl.app.core.security.token.TokenConfig;

@Configuration
@RequiredArgsConstructor
@Import({TokenConfig.class, AuthenticationConfig.class, AuditorConfig.class})
public class SecurityConfig {
}
