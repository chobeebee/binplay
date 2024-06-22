package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="statistics")
@NoArgsConstructor
public class Statistics extends Timestamped{
    @Id
    @Column(name = "stat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statId;

    @Column(name="period", nullable = false)
    private String period;

    @Column(name="period_views", nullable = false)
    private Long periodViews;

    @Column(name="total_viewing_time")
    private Integer totalViewingTime;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Videos video;
}
