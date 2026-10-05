package org.example.oauth.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    @Column(nullable = false)
    private String providerId;

    @Nullable
    private String email;

    @Column(nullable = false)
    private String nickname;

    public Member(
            AuthProvider provider,
            String providerId,
            @Nullable String email,
            String name
    ) {
        this.provider = provider;
        this.providerId = providerId;
        this.email = email;
        this.nickname = name;
    }
}