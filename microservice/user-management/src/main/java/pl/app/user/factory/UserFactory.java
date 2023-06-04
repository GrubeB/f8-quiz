package pl.app.user.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.app.core.factory.EntityFactory;
import pl.app.user.domain.User;
import pl.app.user.dto.CreateUserDto;
import pl.app.user.dto.UserRegisterDto;

@Component
@RequiredArgsConstructor
public class UserFactory implements EntityFactory<User, CreateUserDto> {
    private final PasswordEncoder passwordEncoder;
    @Override
    public User buildEntityFromDto(CreateUserDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
    }
    public User buildEntityFromDto(UserRegisterDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
    }
}
