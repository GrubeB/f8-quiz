package pl.app.core.security.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

//@Configuration
public class AuthenticationConfig {
    @Bean
    public AuthenticationService authenticationService(AuthenticationManager authenticationManager) {
        return new AuthenticationServiceImpl(authenticationManager);
    }

    @Bean
    public AuthorizationService authorizationService() {
        return new AuthorizationServiceImpl();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
