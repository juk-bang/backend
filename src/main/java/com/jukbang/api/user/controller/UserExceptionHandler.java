package com.jukbang.api.user.controller;

import com.jukbang.api.common.response.ErrorResponse;
import com.jukbang.api.user.exception.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 사용자 관련해 발생하는 Exception Handler
 *
 * @author always0ne
 * @version 1.0
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler {
    /**
     * 사용자가 없는 경우
     *
     * @param exception 없는 사용자 예외
     * @return BAD_REQUEST
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIdExists(UserNotFoundException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "2001", exception.getMessage());
    }
}
