package com.jukbang.api.room.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    @Column(name = "monthly_price")
    double monthlyPrice;
    @Column(name = "admin_expenses")
    double adminExpenses;
    @Column(name = "deposit")
    double deposit;
}
