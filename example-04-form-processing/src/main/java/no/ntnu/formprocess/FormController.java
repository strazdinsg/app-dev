package no.ntnu.formprocess;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    /**
     * This URL will show an HTML form with the post option. The form itself is placed within the Thymeleaf template,
     * see /src/main/resources/templates
     *
     * @return Name of the Thymeleaf template to render
     */
    @GetMapping("/")
    public String showForm() {
        return "showForm";
    }

    /**
     * This endpoint parses the HTTP POST request when the form is submitted - we simply collect the data and pass it
     * to the Thymeleaf HTML  template which will show it.
     * see /src/main/resources/templates
     *
     * @param feedback Form data, injected (collected) in a Feedback object
     * @param model    the data model to pass to Thymeleaf template engine
     * @return Name of the Thymeleaf template to render
     */
    @PostMapping("/hello")
    public String handlePost(@ModelAttribute Feedback feedback, Model model) {
        model.addAttribute("feedback", feedback);
        return "showFormResults";
    }
}
