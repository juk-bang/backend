package com.jukbang.api.room.dto;

import com.jukbang.api.room.entity.Review;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
  private long id;
  private String writer;
  private String message;
  private double grade;
  private LocalDateTime modifiedDate;

  public ReviewDto(Review review) {
    this.id = review.getId();
    this.writer = review.getWriter().getUserId();
    this.message = review.getMessage();
    this.grade = review.getGrade();
    this.modifiedDate = review.getModifiedDate();
  }
}
