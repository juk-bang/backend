package com.jukbang.api.community.exception;

/**
 * 존재하지 않는 commentsId
 * 존재하지 않는 댓글입니다.
 *
 * @author gyedaehwan
 * @version 1.0
 */
public class CommentsNotFoundException extends RuntimeException {
    /**
     * comments : 존재하지 않는 comments
     *
     */
    public CommentsNotFoundException() {
        super("존재하지 않는 댓글입니다.");
    }
}
