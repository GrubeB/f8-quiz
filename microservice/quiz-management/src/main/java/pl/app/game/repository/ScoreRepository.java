package pl.app.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.app.game.domain.Participant;
import pl.app.game.domain.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
