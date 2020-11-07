package com.jukbang.api.room.exception;

/**
 * 존재하지 않는 리뷰입니다.
 *
 * @author always0ne
 * @version 1.0
 */
public class ReviewNotFoundException extends RuntimeException {
    /**
     * 없는 리뷰입니다.
     */
    public ReviewNotFoundException() {
        super("존재하지 않는 리뷰입니다.");
    }
}
