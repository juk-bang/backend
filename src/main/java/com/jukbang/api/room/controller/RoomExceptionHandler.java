package com.jukbang.api.room.controller;

import com.jukbang.api.common.response.ErrorResponse;
import com.jukbang.api.room.exception.NotYourRoomException;
import com.jukbang.api.room.exception.RoomNotFoundException;
import com.jukbang.api.security.exception.CantSignInException;
import com.jukbang.api.security.exception.IdAlreadyExistsException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Room service에서 발생하는 Exception Handler
 *
 * @author always0ne
 * @version 1.0
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RoomExceptionHandler {
    /**
     * 존재하지 않는 방 예외
     *
     * @param exception 아이디 중복 예외
     * @return ACCEPTED
     */
    @ExceptionHandler(RoomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleIdExists(RoomNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, "1001", exception.getMessage());
    }

    /**
     * 수정 권한 없는 방 예외
     *
     * @param exception 인증 불가 예외
     * @return FORBIDDEN
     */
    @ExceptionHandler(NotYourRoomException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleUserNotFound(NotYourRoomException exception) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "1002", exception.getMessage());
    }
}
