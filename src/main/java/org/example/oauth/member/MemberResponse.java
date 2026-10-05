package org.example.oauth.member;

public record MemberResponse (
        Long id,
        AuthProvider provider,
        String providerId,
        String email,
        String name
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getProvider(),
                member.getProviderId(),
                member.getEmail(),
                member.getNickname()
        );
    }
}
