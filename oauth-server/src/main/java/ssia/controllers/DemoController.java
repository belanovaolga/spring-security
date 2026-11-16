package ssia.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final OAuth2AuthorizedClientManager clientManager;

    @GetMapping("/demo")
    public String demo() {
        return "Demo";
    }

    @GetMapping("/token")
    public String token() {

        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId("1")
                .principal("client")
                .build();
        var client = clientManager.authorize(request);

        return client.getAccessToken().getTokenValue();
    }
}
