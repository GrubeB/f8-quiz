package pl.app.backend.quiz.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer  {
    private Long id;
    @Size(min = 1, max = 1024)
    private String context;
    @NotNull
    private Boolean correct;
}
