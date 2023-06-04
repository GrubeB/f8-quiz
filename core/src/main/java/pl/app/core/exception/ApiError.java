package pl.app.core.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import pl.app.core.dto.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
@DTO
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ApiError implements Serializable {
    private String path;
    private String message;
    private Integer statusCode;
    private LocalDateTime dateTime;
}
