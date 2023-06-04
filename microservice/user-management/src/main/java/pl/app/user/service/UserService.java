package pl.app.user.service;

import jakarta.validation.Valid;
import pl.app.core.service.dto.CreatableDtoService;
import pl.app.core.service.dto.DeletableDtoByIdService;
import pl.app.core.service.dto.FetchableAllDtoService;
import pl.app.core.service.dto.FetchableDtoByIdService;
import pl.app.user.domain.User;
import pl.app.user.dto.CreateUserDto;
import pl.app.user.dto.UserDto;
import pl.app.user.dto.UserRegisterDto;
import pl.app.user.dto.ChangePasswordDto;

public interface UserService extends
        CreatableDtoService<Long, User, UserDto, CreateUserDto>,
        DeletableDtoByIdService<Long, User>,
        FetchableAllDtoService<Long, User, UserDto>,
        FetchableDtoByIdService<Long, User, UserDto> {
    UserDto register(@Valid UserRegisterDto dto);
}
