package no.ntnu.formprocess;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {

    /**
     * This endpoint parses the HTTP POST request when the form is submitted
     * - we simply collect the data and form a string with it in the response
     *
     * @param feedback Form data, injected (collected) in a Feedback object
     * @return The string to be returned to the client
     */
    @PostMapping("/hello")
    public Feedback handlePost(@ModelAttribute Feedback feedback) {
        return feedback;
    }
}
