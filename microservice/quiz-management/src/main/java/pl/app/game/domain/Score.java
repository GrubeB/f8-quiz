package pl.app.game.domain;

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
@Table(name = "t_score")
public class Score extends AbstractEntity<Long> {
    private Double points;

    public void addPoints(Double numberOfPoints) {
        points+=numberOfPoints;
    }
}
