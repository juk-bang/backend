package com.jukbang.api.user.exception;

/**
 * 찜 조회 불가
 * 찜정보가 존재하지 않습니다.
 *
 * @author always0ne
 * @version 1.0
 */
public class FavoriteNotFoundException extends RuntimeException {
    /**
     */
    public FavoriteNotFoundException() {
        super("찜정보가 존재하지 않습니다.");
    }
}
