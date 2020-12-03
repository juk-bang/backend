package com.jukbang.api.user.entity.embedded;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Embeddable
@AllArgsConstructor
public class Filter {
  private Double scaleL;
  private Double scaleH;
  private Double monthlyLeaseL;
  private Double monthlyLeaseH;
  private Double adminExpensesL;
  private Double adminExpensesH;
  private Double depositL;
  private Double depositH;
  private Double gradeL;
  private Double gradeH;
  private Double distanceL;
  private Double distanceH;
  private Integer layout;
  private Double floor;

  public Filter() {
    this.layout = 0;
    this.floor = 0.0;
    this.scaleL = 0.0;
    this.scaleH = 0.0;
    this.monthlyLeaseL = 0.0;
    this.monthlyLeaseH = 0.0;
    this.adminExpensesL = 0.0;
    this.adminExpensesH = 0.0;
    this.depositL = 0.0;
    this.depositH = 0.0;
    this.gradeL = 0.0;
    this.gradeH = 0.0;
    this.distanceL = 0.0;
    this.distanceH = 0.0;
  }

  public void updateFilter(Integer layout, Double floor, Double[] scales, Double[] monthlyLeases,
                           Double[] adminExpenses, Double[] deposit,
                           Double[] grades, Double[] distances) {
    if (layout != null) {
      this.layout = layout;
    }
    if (floor != null) {
      this.floor = floor;
    }
    if (scales != null) {
      if (scales[0] != null) {
        this.scaleL = scales[0];
      }
      if (scales[1] != null) {
        this.scaleH = scales[1];
      }
    }
    if (monthlyLeases != null) {
      if (monthlyLeases[0] != null) {
        this.monthlyLeaseL = monthlyLeases[0];
      }
      if (monthlyLeases[1] != null) {
        this.monthlyLeaseH = monthlyLeases[1];
      }
    }
    if (adminExpenses != null) {
      if (adminExpenses[0] != null) {
        this.adminExpensesL = adminExpenses[0];
      }
      if (adminExpenses[1] != null) {
        this.adminExpensesH = adminExpenses[1];
      }
    }
    if (deposit != null) {
      if (deposit[0] != null) {
        this.depositL = deposit[0];
      }
      if (deposit[1] != null) {
        this.depositH = deposit[1];
      }
    }
    if (grades != null) {
      if (grades[0] != null) {
        this.gradeL = grades[0];
      }
      if (grades[1] != null) {
        this.gradeH = grades[1];
      }
    }
    if (distances != null) {
      if (distances[0] != null) {
        this.distanceL = distances[0];
      }
      if (distances[1] != null) {
        this.distanceH = distances[1];
      }
    }
  }

}
