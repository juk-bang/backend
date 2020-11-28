package com.jukbang.api.room.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UnivFilter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long univId;

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

  @JsonIgnore
  private long accessNum;

  public void updateFilter(Double[] scales, Double[] monthlyLeases,
                           Double[] adminExpenses, Double[] deposit,
                           Double[] grades, Double[] distances) {
    this.scaleL = plusAvg(this.scaleL, scales[0]);
    this.scaleH = plusAvg(this.scaleH, scales[1]);
    this.monthlyLeaseL = plusAvg(this.monthlyLeaseL, monthlyLeases[0]);
    this.monthlyLeaseH = plusAvg(this.monthlyLeaseH, monthlyLeases[1]);
    this.adminExpensesL = plusAvg(this.adminExpensesL, adminExpenses[0]);
    this.adminExpensesH = plusAvg(this.adminExpensesH, adminExpenses[1]);
    this.depositL = plusAvg(this.depositL, deposit[0]);
    this.depositH = plusAvg(this.depositH, deposit[1]);
    this.gradeL = plusAvg(this.gradeL, grades[0]);
    this.gradeH = plusAvg(this.gradeH, grades[1]);
    this.distanceL = plusAvg(this.distanceL, distances[0]);
    this.distanceH = plusAvg(this.distanceH, distances[1]);
    this.accessNum++;
  }

  private Double plusAvg(Double val, Double newVal) {
    if (newVal != null) {
      if (val == null) {
        val = 0.0;
      }
      return (val * this.accessNum + newVal) / (this.accessNum + 1);
    } else {
      return val;
    }
  }

}
