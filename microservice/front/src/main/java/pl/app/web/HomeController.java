package pl.app.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.app.config.thymeleaf.ModelAndViewService;

@Controller
@RequestMapping(HomeController.controllerPath)
@RequiredArgsConstructor
public class HomeController {
    public static final String controllerPath = "";

    @GetMapping(path = {"", "/"})
    ModelAndView home() {
        return ModelAndViewService.clean("home");
    }
}
