package pl.app.backend.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private Long id;
    private String name;
    private Score score;
    private Integer numberOfResponds;
}
