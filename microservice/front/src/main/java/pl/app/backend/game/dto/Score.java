package pl.app.backend.game.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score  {
    private Long id;
    private Double points;

    public void addPoints(Double numberOfPoints) {
        points+=numberOfPoints;
    }
}
