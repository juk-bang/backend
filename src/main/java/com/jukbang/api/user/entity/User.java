package com.jukbang.api.user.entity;

import com.jukbang.api.common.entity.Time;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Time {
    @Id
    /**
     *  회원의 고유번호 (중복 불가)
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


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
    private List<String> roles;

    /**
     * Refresh Token
     */
    private String refreshToken;


    /**
     * Refresh Token 갱신
     *
     * @param refreshToken RefreshToken
     */
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
