package pl.app.core.security.user_details;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;

@Getter
public class CustomUserDetails extends User {
    private final Long id;

    public CustomUserDetails(Long userId, String email, List<? extends GrantedAuthority> authorities) {
        super(email, "", authorities);
        this.id = userId;
    }

    public CustomUserDetails(Long userId, String email, String password, List<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.id = userId;
    }

    public CustomUserDetails(Long userId, String email, String password, Set<String> authorities) {
        super(email, password, toGrantedAuthority(authorities));
        this.id = userId;
    }

    private static List<? extends GrantedAuthority> toGrantedAuthority(Set<String> grantAuthorityStringSet) {
        return grantAuthorityStringSet.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
