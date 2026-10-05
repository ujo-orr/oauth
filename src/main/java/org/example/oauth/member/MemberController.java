package org.example.oauth.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public MemberResponse me(OAuth2AuthenticationToken authentication) {

        String registrationId = authentication.getAuthorizedClientRegistrationId();

        AuthProvider authProvider = AuthProvider.valueOf(
                registrationId.toUpperCase(Locale.ROOT));

        String providerId = authentication.getName();

        return memberService.findMember(authProvider, providerId);
    }
}
