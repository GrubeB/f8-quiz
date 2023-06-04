package pl.app.game.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.app.game.domain.Game;
import pl.app.game.domain.Participant;
import pl.app.game.domain.Score;
import pl.app.game.repository.ParticipantRepository;
import pl.app.game.repository.ScoreRepository;

@Service
@Getter
@Transactional
@RequiredArgsConstructor
class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository repository;
    private final ScoreRepository scoreRepository;
    private final GameService gameService;


    @Override
    public Participant create(String gameCode, String name) {
        Game gameByCode = gameService.getGameByCode(gameCode);
        Score score = scoreRepository.save(new Score(0.0));
        Participant participant = new Participant(name, score,0);
        gameByCode.addParticipant(participant);
        return repository.save(participant);
    }
}
