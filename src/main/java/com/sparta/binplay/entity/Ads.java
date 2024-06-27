package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name="ads")
@NoArgsConstructor
public class Ads extends Timestamped{
    @Id
    @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adId;

    @OneToMany(mappedBy = "ad")
    private List<VideoAd> videoAds;
}
