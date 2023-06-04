package pl.app.game.service;

import pl.app.core.service.entity.FetchableAllService;
import pl.app.core.service.entity.FetchableByIdService;
import pl.app.core.service.entity.UpdatableService;
import pl.app.game.domain.Game;
import pl.app.quiz.domain.Question;

import java.util.Set;

public interface GameService extends
        FetchableAllService<Long, Game>,
        FetchableByIdService<Long, Game>,
        UpdatableService<Long, Game> {

    Game getGameByCode(String code);
    Game create(Long quizId);
    Question getNextQuestion(Long gameId, Long participantId);
    boolean sendRespond(Long gameId, Long participantId, Long questionId, Set<Long> answerIds);
}
