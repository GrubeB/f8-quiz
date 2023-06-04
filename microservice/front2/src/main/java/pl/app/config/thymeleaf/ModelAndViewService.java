package pl.app.config.thymeleaf;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewService {
    public static ModelAndView clean(String viewName) {
        return new ModelAndView(viewName);
    }
}
