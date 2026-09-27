package org.example.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        return Map.of(
                "authenticationType", authentication.getClass().getSimpleName(),
                "principalType", oidcUser.getClass().getSimpleName(),
                "authName", authentication.getName(),
                "oidcName", oidcUser.getName(),
                "email", oidcUser.getEmail(),
                "authAuthorities", authentication.getAuthorities(),
                "oidcAuthorities", oidcUser.getAuthorities()
        );
    }
}
