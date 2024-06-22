package com.sparta.binplay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="streams")
@NoArgsConstructor
public class Streams extends Timestamped{
    @Id
    @Column(name = "stream_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streamId;

    @Column(name="viewing_time", nullable = false)
    private Integer viewing_time;

    @Column(name="paused_at", nullable = false)
    private Integer paused_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Videos video;
}
