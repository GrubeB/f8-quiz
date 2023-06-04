package pl.app.user.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import pl.app.core.dto.DTO;
import pl.app.core.validation.Password;

import java.io.Serializable;

@DTO
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ChangePasswordDto implements Serializable {
    String currentPassword;
    @Password
    String newPassword;
}
