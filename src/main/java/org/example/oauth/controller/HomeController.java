package org.example.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/user")
    public Map<String, Object> user(
            @AuthenticationPrincipal OidcUser oidcUser
    ) {
        return Map.of(
                "sub", oidcUser.getSubject(),
                "email",oidcUser.getEmail(),
                "name", oidcUser.getFullName(),
                "picture", oidcUser.getPicture()
        );
    }

    @GetMapping("/auth")
    public Map<String, Object> auth(Authentication authentication) {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        return Map.of(
                "authenticationType",
                authentication.getClass().getSimpleName(),

                "principalType",
                oauth2User.getClass().getSimpleName(),

                "authName",
                authentication.getName(),

                "oauthName",
                oauth2User.getName(),

                "authorities",
                authentication.getAuthorities(),

                "attributes",
                oauth2User.getAttributes()
        );
    }
}
