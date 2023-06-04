package pl.app.quiz.service;

import pl.app.core.service.entity.*;
import pl.app.quiz.domain.Question;
import pl.app.quiz.domain.Quiz;

public interface QuestionService extends
        DeletableByIdService<Long, Question>,
        UpdatableService<Long, Question>,
        FetchableAllService<Long, Question>,
        FetchableByIdService<Long, Question> {
    Question createEntity(Long quizId, Question question);
}
