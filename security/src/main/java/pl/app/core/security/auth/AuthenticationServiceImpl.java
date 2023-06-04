package pl.app.core.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.app.core.security.user_details.CustomDetails;
import pl.app.core.security.user_details.CustomUserDetails;

import java.util.Objects;
import java.util.Optional;

class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Optional<String> getAccessToken() {
        Authentication authentication = getCurrentAuthentication();
        if (Objects.nonNull(authentication)) {
            Object details = authentication.getDetails();
            if (details instanceof CustomDetails customDetails) {
                return Optional.of(customDetails.getTokens().get("access_token"));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getCurrentUserName() {
        Authentication authentication = getCurrentAuthentication();
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails userDetails) {
                return Optional.of(userDetails.getUsername());
            }
            if (principal instanceof String name) {
                return Optional.of(name);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Long> getCurrentUserId() {
        Authentication authentication = getCurrentAuthentication();
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails userDetails) {
                return Optional.of(userDetails.getId());
            }
        }
        return Optional.empty();
    }

    @Override
    public Authentication authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    public void setCurrentAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
