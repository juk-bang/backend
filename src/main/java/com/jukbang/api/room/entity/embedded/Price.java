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
    double monthlyLease;
    double adminExpenses;
    double deposit;
}
