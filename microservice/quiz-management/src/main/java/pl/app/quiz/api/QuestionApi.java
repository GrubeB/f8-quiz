package pl.app.quiz.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.app.core.api.entity.*;
import pl.app.core.util.EntityLocationUriUtils;
import pl.app.quiz.domain.Question;
import pl.app.quiz.domain.Quiz;
import pl.app.quiz.service.QuestionService;
import pl.app.quiz.service.QuizService;

@RestController
@RequestMapping(QuestionApi.resourcePath)
@RequiredArgsConstructor
@Getter
class QuestionApi implements
        DeletableApi<Long, Question>,
        UpdatableApi<Long, Question>,
        FetchableAllApi<Long, Question>,
        FetchableByIdApi<Long, Question> {
    public static final String resourceName = "questions";
    public static final String resourcePath = "/api/v1/quizzes/{quizId}/" + resourceName;
    public final QuestionService service;

    @PostMapping
    ResponseEntity<Question> create(@PathVariable Long quizId, @RequestBody Question entity, HttpServletRequest request) {
        Question savedEntity = service.createEntity(quizId, entity);
        return ResponseEntity
                .created(EntityLocationUriUtils.createdEntityLocationURI(savedEntity.getId(), request.getRequestURI()))
                .body(savedEntity);
    }
}
