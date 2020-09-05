package com.jukbang.api.room.exception;

/**
 * 본인의 방이 아닐때 예외
 * 당신 소유의 방이 아닙니다.
 *
 * @author always0ne
 * @version 1.0
 */
public class NotYourRoomException extends RuntimeException {
    /**
     * 당신 소유의 방이 아닙니다.
     */
    public NotYourRoomException() {
        super("당신 소유의 방이 아닙니다.");
    }
}
