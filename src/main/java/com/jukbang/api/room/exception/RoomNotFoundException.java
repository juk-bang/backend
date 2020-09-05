package com.jukbang.api.room.exception;

/**
 * 존재하지 않는 방입니다.
 *
 * @author always0ne
 * @version 1.0
 */
public class RoomNotFoundException extends RuntimeException {
    /**
     * 당신 소유의 방이 아닙니다.
     */
    public RoomNotFoundException() {
        super("존재하지 않는 방입니다.");
    }
}
