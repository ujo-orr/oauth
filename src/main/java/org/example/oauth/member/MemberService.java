package org.example.oauth.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member findOrCreate(
            AuthProvider provider,
            String providerId,
            String email,
            String name
    ) {
        return memberRepository
                .findByProviderAndProviderId(provider, providerId)
                .orElseGet(() -> memberRepository.save(
                        new Member(provider, providerId, email, name)
                ));
    }

    @Transactional(readOnly = true)
    public MemberResponse findMember(
            AuthProvider provider,
            String providerId
    ) {
        Member member = memberRepository
                .findByProviderAndProviderId(provider, providerId)
                .orElseThrow();

        return MemberResponse.from(member);
    }
}
