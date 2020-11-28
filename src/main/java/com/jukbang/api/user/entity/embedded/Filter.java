package com.jukbang.api.user.entity.embedded;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
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

  public void updateFilter(Integer layout, Double floor, Double[] scales, Double[] monthlyLeases, Double[] adminExpenses, Double[] deposit,
                Double[] grades, Double[] distances) {
    this.layout = layout;
    this.floor = floor;
    this.scaleL  = scales[0];
    this.scaleH  = scales[1];
    this.monthlyLeaseL = monthlyLeases[0];
    this.monthlyLeaseH = monthlyLeases[1];
    this.adminExpensesL = adminExpenses[0];
    this.adminExpensesH = adminExpenses[1];
    this.depositL = deposit[0];
    this.depositH = deposit[1];
    this.gradeL = grades[0];
    this.gradeH = grades[1];
    this.distanceL = distances[0];
    this.distanceH = distances[1];
  }

}
