package pl.app.game.service;

import pl.app.core.service.entity.FetchableAllService;
import pl.app.core.service.entity.FetchableByIdService;
import pl.app.game.domain.Game;
import pl.app.game.domain.Participant;
import pl.app.quiz.domain.Question;

import java.util.Set;

public interface ParticipantService extends
        FetchableAllService<Long, Participant>,
        FetchableByIdService<Long, Participant> {
    Participant create(String gameCode, String name);
}
