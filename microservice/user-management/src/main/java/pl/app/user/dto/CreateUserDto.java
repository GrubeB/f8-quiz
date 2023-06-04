package pl.app.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
public class CreateUserDto implements Serializable {
    @Size(min = 6, max = 30)
    private String firstName;
    @Size(min = 6, max = 30)
    private String lastName;
    @Email
    private String email;
    @Password
    private String password;
}
