package pl.app.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;
import pl.app.core.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_user")
public class User extends AbstractEntity<Long> {
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @NotBlank
    @Column(nullable = false)
    private String password;
    @NotNull
    private Long modelRolesId;
}
