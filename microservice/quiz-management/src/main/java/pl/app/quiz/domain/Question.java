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
@Table(name = "t_question")
public class Question extends AbstractEntity<Long> {
    private QuestionType type;
    @Column(nullable = false)
    private String context;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "question_id")
    private Set<Answer> answers;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name="code", length = 102400)
    private String code;
    private String explanation;
    private String category;
    @Column(name = "number_of_points")
    private Double numberOfPoints;
}
