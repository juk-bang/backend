package com.jukbang.api.community_student.exception;

/**
 * 존재하지 않는 postId
 * 존재하지 않는 게시글입니다.
 *
 * @author gyedaehwan
 * @version 1.0
 */
public class PostNotFoundException extends RuntimeException {
    /**
     * Post : 존재하지 않는 post
     *
     */
    public PostNotFoundException() {
        super("존재하지 않는 게시글입니다.");
    }
}
