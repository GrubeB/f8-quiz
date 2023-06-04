package pl.app.quiz.service;

import pl.app.core.service.entity.*;
import pl.app.quiz.domain.Question;
import pl.app.quiz.domain.Quiz;

public interface QuizService extends
        CreatableService<Long, Quiz>,
        UpdatableService<Long, Quiz>,
        DeletableByIdService<Long, Quiz>,
        FetchableAllService<Long, Quiz>,
        FetchableByIdService<Long, Quiz> {
}
