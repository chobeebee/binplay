package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="videos")
@NoArgsConstructor
public class Videos extends Timestamped{
    @Id
    @Column(name = "video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="views", nullable = false)
    private Long views;

    @Column(name="video_length", nullable = false)
    private Integer videoLength;

    @Column(name="ad_position", nullable = false)
    private Integer adPosition;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "video")
    private List<Streams> streams;

    @OneToMany(mappedBy = "video")
    private List<Ads> ads;

    @OneToMany(mappedBy = "video")
    private List<Statistics> statistics;
}