package pl.app.user.mappers;

import org.mapstruct.Mapper;
import pl.app.core.mapper.DtoMapper;
import pl.app.core.mapper.MapStructConfig;
import pl.app.user.domain.User;
import pl.app.user.dto.UserDto;

@Mapper(config = MapStructConfig.class)
public interface UserMapper extends DtoMapper<User, UserDto> {
}
