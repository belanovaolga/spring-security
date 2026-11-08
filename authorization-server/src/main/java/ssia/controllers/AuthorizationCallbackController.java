package ssia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationCallbackController {

    @GetMapping("/authorized")
    public String authorized() {
        return "Use the code from the URL in a POST request to /oauth2/token.";
    }
}
