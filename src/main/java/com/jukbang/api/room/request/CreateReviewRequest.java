package com.jukbang.api.room.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {
    private String message;
    private double grade;
}
