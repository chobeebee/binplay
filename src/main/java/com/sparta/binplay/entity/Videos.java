package com.sparta.binplay.entity;

import com.sparta.binplay.dto.request.VideoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "videos")
@NoArgsConstructor
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

    public Videos(String title, String description, long videoLength) {
        this.title = title;
        this.description = description;
//        this.views = videoRequestDto.getViews();
        this.videoLength = videoLength;
        //this.user = videoRequestDto.getUser();
    }

    public static Videos of(Users user, VideoRequestDto videoRequestDto) {
        Videos videos = new Videos();
        videos.setUser(user);
        videos.setTitle(videoRequestDto.getTitle());
        videos.setDescription(videoRequestDto.getDescription());
        videos.setVideoLength(videoRequestDto.getVideoLength());
        return videos;
    }

    public void viewUp() {
        this.views++;
    }
//
//    @OneToMany(mappedBy = "videos", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Streams> streams;
//
//    @OneToMany(mappedBy = "videos", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<VideoAd> videoAd;
//
//    @OneToMany(mappedBy = "videos", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Statistics> statistics;

}
