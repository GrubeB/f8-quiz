package pl.app.backend.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.app.core.security.auth.AuthenticationService;

@Configuration
@EnableFeignClients
@RequiredArgsConstructor
public class OAuthFeignConfig {
    private final AuthenticationService authenticationService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            authenticationService.getAccessToken().ifPresent(token ->
                    requestTemplate.header("Authorization", "Bearer " + token)
            );
        };
    }
}
