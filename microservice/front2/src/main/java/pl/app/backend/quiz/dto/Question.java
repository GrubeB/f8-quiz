package pl.app.backend.quiz.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Question {
    private Long id;
    @NotNull
    private QuestionType type;
    @Size(min = 3, max = 1024)
    private String context;
    @NotEmpty
    private List< @Valid Answer> answers;
    private String explanation;
    private String category;
    @Min(value = 0)
    private Double numberOfPoints;

    public Question() {
        answers = new ArrayList<>();
        answers.add(new Answer(null,"A", true));
        answers.add(new Answer(null,"B", false));
        answers.add(new Answer(null,"C", false));
        answers.add(new Answer(null,"D", false));
    }
}
