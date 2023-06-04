package pl.app.user.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.app.core.api.dto.CreatableDtoApi;
import pl.app.core.api.dto.DeletableDtoApi;
import pl.app.core.api.dto.FetchableAllDtoApi;
import pl.app.core.api.dto.FetchableDtoByIdApi;
import pl.app.user.domain.User;
import pl.app.user.dto.CreateUserDto;
import pl.app.user.dto.UserDto;
import pl.app.user.dto.UserRegisterDto;
import pl.app.user.service.UserService;

@RestController
@RequestMapping(UserApi.resourcePath)
@RequiredArgsConstructor
@Getter
class UserApi implements
        CreatableDtoApi<Long, User, UserDto, CreateUserDto>,
        DeletableDtoApi<Long, User>,
        FetchableAllDtoApi<Long, User, UserDto>,
        FetchableDtoByIdApi<Long, User, UserDto> {
    public static final String resourceName = "users";
    public static final String resourcePath = "/api/v1/" + resourceName;

    public final UserService service;
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }
}
