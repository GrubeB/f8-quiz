package pl.app.backend.game.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.app.backend.quiz.dto.Quiz;


import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game  {
    private String quizCode;
    private Quiz quiz;
    private Set<Participant> participants;
    private Boolean ended;
    private Long id;
}
