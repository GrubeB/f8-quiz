package pl.app.core.security.token;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

public interface TokenService {
    String generateToken(Authentication authentication);
    String generateToken(Authentication authentication, Map<String, Object> extraClaims);
    boolean isTokenValid(String token);
    Authentication extractAuthentication(String token);
    List<? extends GrantedAuthority> extractAuthorities(String token);
    String resolveBearerTokenFromRequest(HttpServletRequest request);
}
