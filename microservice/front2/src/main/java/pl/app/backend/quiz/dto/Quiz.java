package pl.app.backend.quiz.dto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz  {
    private Long id;
    private Long userId;
    @Size(min = 3, max = 20)
    private String name;
    private Set<Question> questions;
    public void addQuestion(Question question) {
        questions.add(question);
    }
    public void removeQuestion(Question question) {
        questions.remove(question);
    }
}
