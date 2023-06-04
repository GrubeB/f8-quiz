package pl.app.game.domain;


import jakarta.persistence.*;
import lombok.*;
import pl.app.core.entity.AbstractEntity;
import pl.app.quiz.domain.Quiz;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_game")
public class Game  extends AbstractEntity<Long> {
    @Column(name="quiz_code",nullable = false, unique = true)
    private String quizCode;
    @OneToOne
    @JoinColumn(
            name = "quiz_id",
            referencedColumnName = "id"
    )
    private Quiz quiz;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="game_id")
    private Set<Participant> participants;
    private Boolean ended;

    public void addParticipant(Participant participant){
        participants.add(participant);
    }
    public void removeParticipant(Participant participant){
        participants.remove(participant);
    }
}
