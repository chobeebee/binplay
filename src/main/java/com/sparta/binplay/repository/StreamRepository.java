package com.sparta.binplay.repository;

import com.sparta.binplay.entity.Streams;
import com.sparta.binplay.entity.Users;
import com.sparta.binplay.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreamRepository extends JpaRepository<Streams, Long> {
    Optional<Streams> findByUsersAndVideos(Users users, Videos videos);
    //Optional<Streams> findByUserIdAndVideoId(Long userId, Long videoId);
}
