package pl.app.user.service;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.app.core.exception.ValidationException;
import pl.app.user.domain.User;
import pl.app.user.dto.ChangePasswordDto;
import pl.app.user.dto.UserDto;
import pl.app.user.dto.UserRegisterDto;
import pl.app.user.factory.UserFactory;
import pl.app.user.mappers.UserMapper;
import pl.app.user.repository.UserRepository;

@Service
@Getter
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserFactory factory;

    @Override
    public UserDto register(@Valid UserRegisterDto dto) {
        User user = factory.buildEntityFromDto(dto);
        User savedUser = createEntity(user);
        return mapper.mapEntityToDto(savedUser);
    }

    @Override
    public void beforeCreateEntity(User user) {
        verifyEmailAvailability(user.getEmail());
        user.setModelRolesId(0L);
    }

    private void verifyEmailAvailability(String email) throws ValidationException {
        if(repository.existsByEmailIgnoreCase(email)){
            throw new ValidationException("Provided email is already in use");
        }
    }
}
