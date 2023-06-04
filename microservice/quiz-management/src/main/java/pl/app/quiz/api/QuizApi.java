package pl.app.quiz.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.app.core.api.entity.*;
import pl.app.quiz.domain.Question;
import pl.app.quiz.domain.Quiz;
import pl.app.quiz.service.QuizService;

@RestController
@RequestMapping(QuizApi.resourcePath)
@RequiredArgsConstructor
@Getter
class QuizApi implements
        CreatableApi<Long, Quiz>,
        DeletableApi<Long, Quiz>,
        UpdatableApi<Long, Quiz>,
        FetchableAllApi<Long, Quiz>,
        FetchableByIdApi<Long, Quiz> {
    public static final String resourceName = "quizzes";
    public static final String resourcePath = "/api/v1/" + resourceName;

    public final QuizService service;
}
