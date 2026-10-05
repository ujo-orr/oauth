package org.example.oauth.oauth;

import lombok.RequiredArgsConstructor;
import org.example.oauth.member.AuthProvider;
import org.example.oauth.member.MemberService;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@NullMarked
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
            throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();

        if("kakao".equals(registrationId)){
            saveKakaoMember(oAuth2User);
        }
        return oAuth2User;
    }

    private void saveKakaoMember(OAuth2User oAuth2User) {

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String providerId = String.valueOf(attributes.get("id"));

        Map<String, Object> kakaoAccount = (
                Map<String, Object>) attributes.get("kakao_account");

        Map<String, Object> profile = (
                Map<String, Object>) kakaoAccount.get("profile");

        String nickname = (String) profile.get("nickname");

        memberService.findOrCreate(
                AuthProvider.KAKAO,
                providerId,
                null,
                nickname
        );
    }
}
