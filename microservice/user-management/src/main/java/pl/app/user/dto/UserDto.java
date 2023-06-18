package pl.app.user.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.app.core.dto.BaseDto;
import pl.app.core.dto.DTO;

@DTO
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto<Long> {
    private String email;
    private String firstName;
    private String lastName;
    private Long modelRolesId;
}
