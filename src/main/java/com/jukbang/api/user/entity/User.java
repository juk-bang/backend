package com.jukbang.api.user.entity;

import com.jukbang.api.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    /**
     *  회원의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;


    /**
     * 회원의 id
     */
    @Column(length = 30, nullable = false)
    private String userId;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 대학 번호
     */
    @Column(nullable = false)
    private int univId;

    /**
     * 사용자 권한
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<UserRole> roles;

    /**
     * Refresh Token
     */
    private String refreshToken;
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;

    /**
     * Refresh Token 갱신
     *
     * @param refreshToken RefreshToken
     */
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
