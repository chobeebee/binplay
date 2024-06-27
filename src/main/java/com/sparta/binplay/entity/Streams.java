package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="streams")
@NoArgsConstructor
public class Streams extends Timestamped{
    @Id
    @Column(name = "stream_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streamId;

    @Column(name="viewing_time", nullable = false)
    private Integer viewingTime;

    @Column(name="paused_at", nullable = false)
    private Integer pausedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Videos video;
}
