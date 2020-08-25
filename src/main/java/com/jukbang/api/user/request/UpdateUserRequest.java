package com.jukbang.api.user.request;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private long id;
    private String userId;
    private int univid;
}
