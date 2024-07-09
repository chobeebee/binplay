package com.sparta.binplay.entity.statistic;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@EqualsAndHashCode
public class StatisticVideoId implements Serializable {
    private LocalDate createdAt;
    private Long video;
}
