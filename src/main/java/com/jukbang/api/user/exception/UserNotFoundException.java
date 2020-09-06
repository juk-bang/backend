package com.jukbang.api.user.exception;

/**
 * 존재하지 않는 userId
 * 존재하지 않는 계정입니다.
 *
 * @author gyedaehwan
 * @version 1.0
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * ID: 존재하지 않는 userId
     *
     */
    public UserNotFoundException() {
        super("존재하지 않는 계정입니다.");
    }
}
