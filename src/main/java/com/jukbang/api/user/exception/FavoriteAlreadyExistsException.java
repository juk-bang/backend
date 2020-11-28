package com.jukbang.api.user.exception;


public class FavoriteAlreadyExistsException extends RuntimeException {

    public FavoriteAlreadyExistsException() {
        super("이미 찜한 방입니다.");
    }
}
