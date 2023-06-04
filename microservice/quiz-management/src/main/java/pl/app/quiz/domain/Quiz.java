package pl.app.quiz.domain;

import jakarta.persistence.*;
import lombok.*;
import pl.app.core.entity.AbstractEntity;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_quiz")
public class Quiz extends AbstractEntity<Long> {
    @Column(name = "user_id", nullable = false)
    private Long userId;
    private String name;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "quiz_id")
    private Set<Question> questions;

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }
}
