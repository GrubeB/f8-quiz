package pl.app.backend.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.app.backend.config.OAuthFeignConfig;

@FeignClient(name = "authenticationServiceApi", url = "http://localhost:9001",configuration = OAuthFeignConfig.class) //TODO
public interface AuthenticationServiceApi {

    @RequestMapping(path = "/api/v1/auth/authenticate", method = RequestMethod.POST)
    String authenticate(AuthenticationDto dto);
}
