package pl.app.backend.quiz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.app.backend.quiz.dto.Quiz;

import java.util.List;

@FeignClient(name = "quizServiceApi", url = "http://localhost:9007") //TODO
public interface QuizServiceApi {

    @RequestMapping(path = "/api/v1/quizzes", method = RequestMethod.GET)
    List<Quiz> fetchAll();

    @RequestMapping(path = "/api/v1/quizzes/{id}", method = RequestMethod.GET)
    Quiz fetch(@PathVariable Long id);

    @RequestMapping(path = "/api/v1/quizzes", method = RequestMethod.POST)
    Quiz create(Quiz quiz);

    @RequestMapping(path = "/api/v1/quizzes/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable Long id);

    @RequestMapping(path = "/api/v1/quizzes/{id}", method = RequestMethod.PUT)
    Quiz update(@PathVariable Long id, Quiz quiz);
}
