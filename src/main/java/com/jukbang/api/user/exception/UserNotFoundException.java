package com.jukbang.api.user.exception;

/**
 * 인증불가 예외
 * ID: 존재하지 않거나 않거나 잠긴 계정입니다.
 *
 * @author always0ne
 * @version 1.0
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * ID: userId 존재하지 않거나 잠긴 계정입니다.
     *
     * @param userId 인증이 불가한 사용자 아이디
     */
    public UserNotFoundException(String userId) {
        super("ID: " + userId + " 존재하지 않거나 잠긴 계정입니다.");
    }
}
