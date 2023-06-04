package pl.app.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.app.backend.quiz.QuestionServiceApi;
import pl.app.backend.quiz.QuizServiceApi;
import pl.app.backend.quiz.dto.Answer;
import pl.app.backend.quiz.dto.Question;
import pl.app.backend.quiz.dto.QuestionType;
import pl.app.backend.quiz.dto.Quiz;
import pl.app.config.thymeleaf.ModelAndViewService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    public static final String controllerPath = "quizzes/{quizId}/questions";

    private final QuizServiceApi quizServiceApi;
    private final QuestionServiceApi questionServiceApi;

    @GetMapping(path = controllerPath)
    ModelAndView view(@PathVariable Long quizId) {
        Quiz fetch = quizServiceApi.fetch(quizId);
        ModelAndView model = ModelAndViewService.clean("questions");
        model.addObject("quiz", fetch);
        model.addObject("questions", fetch.getQuestions());
        return model;
    }

    @GetMapping(path = controllerPath + "/new")
    ModelAndView createNewQuestionView(@PathVariable Long quizId) {
        Quiz fetch = quizServiceApi.fetch(quizId);
        ModelAndView model = ModelAndViewService.clean("question");
        model.addObject("question", new Question());
        model.addObject("quiz", fetch);
        return model;
    }

    @GetMapping(path = controllerPath + "/{questionId}/edit")
    ModelAndView editQuestionView(@PathVariable Long quizId, @PathVariable Long questionId) {
        Quiz fetch = quizServiceApi.fetch(quizId);
        Question question = questionServiceApi.fetch(quizId,questionId);
        ModelAndView model = ModelAndViewService.clean("question");
        model.addObject("question", question);
        model.addObject("quiz", fetch);
        return model;
    }

    @PostMapping(path = {controllerPath, controllerPath + "/{questionId}"})
    String saveQuiz(@PathVariable Long quizId, @PathVariable(required = false) Long questionId,
                    Model model, @Valid Question question, Errors errors, RedirectAttributes attributes) {

        if (errors.hasErrors()) {
            Quiz quiz = quizServiceApi.fetch(quizId);
            model.addAttribute("quiz", quiz);
            return "question";
        }
        if (Objects.nonNull(questionId)) {
            Question existingQuestion = questionServiceApi.fetch(quizId,questionId);
            existingQuestion.setType(question.getType());
            existingQuestion.setContext(question.getContext());
            existingQuestion.setExplanation(question.getExplanation());
            existingQuestion.setCategory(question.getCategory());
            existingQuestion.setNumberOfPoints(question.getNumberOfPoints());
            questionServiceApi.update(quizId, questionId, existingQuestion);
        } else {
            questionServiceApi.create(quizId, question);
        }
        attributes.addFlashAttribute("toastMessage", "Success!");
        attributes.addFlashAttribute("toastClass", "success");

        return "redirect:/quizzes/" + quizId + "/questions";
    }

    @GetMapping(path = controllerPath + "/{questionId}/delete")
    String deleteQuiz(@PathVariable Long quizId, @PathVariable Long questionId,
                      RedirectAttributes attributes) {
        attributes.addFlashAttribute("toastMessage", "Success!");
        attributes.addFlashAttribute("toastClass", "success");
        questionServiceApi.delete(quizId,questionId);
        return "redirect:/quizzes/" + quizId + "/questions";
    }

    @ModelAttribute("questionTypes")
    public QuestionType[] messages() {
        return QuestionType.values();
    }

}
