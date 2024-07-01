package com.sparta.binplay.entity;

import com.sparta.binplay.dto.request.VideoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
//@Setter
@Table(name = "videos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Videos extends Timestamped {
    @Id
    @Column(name = "video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "views", nullable = false)
    private long views;

    @Column(name = "video_length", nullable = false)
    private long videoLength;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "video", cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Streams> streams;

    @OneToMany(mappedBy = "video", cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<VideoAd> videoAd;


    public Videos(String title, String description, long videoLength) {
        this.title = title;
        this.description = description;
//        this.views = videoRequestDto.getViews();
        this.videoLength = videoLength;
        //this.user = videoRequestDto.getUser();
    }

    public static Videos of(Users user, VideoRequestDto videoRequestDto) {
        return Videos.builder()
                .user(user)
                .title(videoRequestDto.getTitle())
                .description(videoRequestDto.getDescription())
                .videoLength(videoRequestDto.getVideoLength())
                .build();
    }

    public void update(VideoRequestDto videoRequestDto) {
        this.title = videoRequestDto.getTitle();
        this.description = videoRequestDto.getDescription();
        this.videoLength = videoRequestDto.getVideoLength();
    }

    public void viewUp() {
        this.views++;
    }


//
//    @OneToMany(mappedBy = "videos", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Statistics> statistics;

}
