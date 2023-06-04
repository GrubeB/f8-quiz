package pl.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import pl.app.backend.auth.AuthenticationDto;
import pl.app.backend.auth.AuthenticationServiceApi;
import pl.app.core.security.auth.AuthenticationConfig;
import pl.app.core.security.token.TokenConfig;
import pl.app.core.security.token.TokenService;

@Configuration
@Import({TokenConfig.class, AuthenticationConfig.class})
public class SecurityConfig {
    private AuthenticationServiceApi authenticationServiceApi;
    private TokenService tokenService;

    public SecurityConfig(@Lazy AuthenticationServiceApi authenticationServiceApi,@Lazy TokenService tokenService) {
        this.authenticationServiceApi = authenticationServiceApi;
        this.tokenService = tokenService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                AuthenticationDto dto = new AuthenticationDto(authentication.getName(), authentication.getCredentials().toString());
                String token = authenticationServiceApi.authenticate(dto);
                return tokenService.extractAuthentication(token);
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
            }
        };
    }
}
