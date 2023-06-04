package pl.app.backend.quiz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.app.backend.quiz.dto.Question;

import java.util.List;

@FeignClient(name = "questionServiceApi", url = "http://localhost:9007") //TODO
public interface QuestionServiceApi {

    @RequestMapping(path = "/api/v1/quizzes/{quizId}/questions", method = RequestMethod.GET)
    List<Question> fetchAll(@PathVariable Long quizId);

    @RequestMapping(path = "/api/v1/quizzes/{quizId}/questions/{id}", method = RequestMethod.GET)
    Question fetch(@PathVariable Long quizId, @PathVariable Long id);

    @RequestMapping(path = "/api/v1/quizzes/{quizId}/questions", method = RequestMethod.POST)
    Question create(@PathVariable Long quizId, Question question);

    @RequestMapping(path = "/api/v1/quizzes/{quizId}/questions/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable Long quizId, @PathVariable Long id);

    @RequestMapping(path = "/api/v1/quizzes/{quizId}/questions/{id}", method = RequestMethod.PUT)
    Question update(@PathVariable Long quizId,@PathVariable Long id, Question question);
}
