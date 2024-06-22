package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ads")
@NoArgsConstructor
public class Ads extends Timestamped{
    @Id
    @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column(name="views", nullable = false)
    private Long views;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Videos video;
}
