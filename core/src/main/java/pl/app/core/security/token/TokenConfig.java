package pl.app.core.security.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TokenConfig {
    @Bean
    public TokenService tokenService() {
        return new TokenServiceImpl();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(TokenService tokenService) {
        return new JwtAuthenticationFilter(tokenService);
    }
}
