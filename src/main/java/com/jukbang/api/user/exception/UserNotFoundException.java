package com.jukbang.api.user.exception;

/**
 * 인증불가 예외
 * 존재하지 않거나 않거나 잠긴 계정입니다.
 *
 * @author always0ne
 * @version 1.0
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * 존재하지 않거나 잠긴 계정입니다.
     *
     */
    public UserNotFoundException() {
        super("존재하지 않거나 잠긴 계정입니다.");
    }
    public UserNotFoundException() {
        super("존재하지 않거나 잠긴 계정입니다.");
    }
}
