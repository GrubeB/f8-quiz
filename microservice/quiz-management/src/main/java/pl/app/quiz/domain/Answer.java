package pl.app.quiz.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import pl.app.core.entity.AbstractEntity;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_answer")
public class Answer extends AbstractEntity<Long> {
    @Column(nullable = false)
    private String context;
    private Boolean correct;
}
