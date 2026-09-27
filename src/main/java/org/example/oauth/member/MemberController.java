package org.example.oauth.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public MemberResponse me(
            @AuthenticationPrincipal OidcUser oidcUser
            ) {
        return memberService.findMember(
                AuthProvider.GOOGLE,
                oidcUser.getSubject()
        );
    }
}
