package pl.app.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.app.core.exception.NotFoundException;
import pl.app.core.security.user_details.CustomUserDetails;
import pl.app.user.domain.User;
import pl.app.user.repository.UserRepository;

import java.util.HashSet;


@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new NotFoundException("Not found object with email: " + email));
        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword(), new HashSet<>());
    }
}
