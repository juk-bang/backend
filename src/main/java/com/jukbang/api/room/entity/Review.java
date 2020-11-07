package com.jukbang.api.room.entity;

import com.jukbang.api.common.entity.Time;
import com.jukbang.api.user.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Review extends Time {
  /**
   * 각 리뷰의 고유번호 (중복 불가)
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /**
   * 100자 이내의 리뷰 입력
   */
  @Column(length = 100, nullable = false)
  private String message;

  /**
   * 리뷰 평점
   */
  @Column(nullable = false)
  private double grade;

  /**
   * 방 정보 번호
   */
  @Column(nullable = false)
  private long roomId;


  @OneToOne
  @JoinColumn(name = "writer_id")
  private User writer;

  public Review(String message, double grade, long roomId, User writer) {
    this.message = message;
    this.grade = grade;
    this.roomId = roomId;
    this.writer = writer;
  }

  public void updateReview(double grade, String message) {
    this.grade= grade;
    this.message = message;
  }
}
