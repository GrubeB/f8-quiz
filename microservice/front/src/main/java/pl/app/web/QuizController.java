package pl.app.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.app.backend.quiz.QuizServiceApi;
import pl.app.backend.quiz.dto.Quiz;
import pl.app.config.thymeleaf.ModelAndViewService;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class QuizController {
    public static final String controllerPath = "quizzes";
    private final QuizServiceApi quizServiceApi;

    @GetMapping(path = controllerPath)
    ModelAndView viewAllView() {
        List<Quiz> quizzes = quizServiceApi.fetchAll();
        ModelAndView model = ModelAndViewService.clean("quizzes");
        model.addObject("quizzes", quizzes);
        return model;
    }

    @GetMapping(path = controllerPath + "/new")
    ModelAndView createNewQuizView() {
        ModelAndView model = ModelAndViewService.clean("quiz");
        model.addObject("quiz", new Quiz());
        return model;
    }

    @GetMapping(path = controllerPath + "/{quizId}/edit")
    ModelAndView editQuestionView(@PathVariable Long quizId) {
        Quiz quiz = quizServiceApi.fetch(quizId);
        ModelAndView model = ModelAndViewService.clean("quiz");
        model.addObject("quiz", quiz);
        return model;
    }

    @PostMapping(path = {controllerPath, controllerPath + "/{quizId}"})
    String saveQuiz(@PathVariable(required = false) Long quizId, Model model,
                    @Valid Quiz quiz, Errors errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            return "quiz";
        }

        if (Objects.nonNull(quizId)) {
            Quiz existingQuiz = quizServiceApi.fetch(quizId);
            existingQuiz.setName(quiz.getName());
            quizServiceApi.update(quizId, existingQuiz);
        } else {
            quizServiceApi.create(quiz);
        }
        attributes.addFlashAttribute("toastMessage", "Success!");
        attributes.addFlashAttribute("toastClass", "success");
        return "redirect:/quizzes";
    }

    @GetMapping(path = controllerPath + "/{quizId}/delete")
    String deleteQuiz(@PathVariable Long quizId, RedirectAttributes attributes) {
        quizServiceApi.delete(quizId);
        attributes.addFlashAttribute("toastMessage", "Success!");
        attributes.addFlashAttribute("toastClass", "success");
        return "redirect:/quizzes";
    }
}
