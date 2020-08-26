package com.jukbang.api.user.request;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private long id;
    private String userId;
    private int univid;
}
