package pl.app.game.domain;

import jakarta.persistence.*;
import lombok.*;
import pl.app.core.entity.AbstractEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_participant")
public class Participant extends AbstractEntity<Long>  {
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "score_id",
            referencedColumnName = "id"
    )
    private Score score;
    @Column(name = "number_of_responds")
    private Integer numberOfResponds;
    public void incrementNumberOfResponds(){
        numberOfResponds+=1;
    }
    public void addPoints(Double numberOfPoints) {
        score.addPoints(numberOfPoints);
    }
}
