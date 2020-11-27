package com.jukbang.api.admin.exception;

/**
 * 존재하지 않는 방입니다.
 *
 * @author always0ne
 * @version 1.0
 */
public class ReportNotFoundException extends RuntimeException {
    /**
     * 당신 소유의 방이 아닙니다.
     */
    public ReportNotFoundException() {
        super("존재하지 않는 신고입니다.");
    }
}
