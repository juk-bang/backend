package com.jukbang.security.request;

import com.jukbang.api.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원가입 요청
 *
 * @author always0ne
 * @version 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {
    /**
     * 사용자 ID
     */
    private String id;
    /**
     * 사용자 비밀번호
     */
    private String password;
    /**
     * 사용자 대학 코드
     */
    private int univId;
    /**
     * 사용자 유형
     */
    private UserRole role;
}
