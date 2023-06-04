package pl.app.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.app.game.domain.Game;
import pl.app.game.domain.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
