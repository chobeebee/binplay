package com.sparta.binplay.repository;

import com.sparta.binplay.entity.Streams;
import com.sparta.binplay.entity.Users;
import com.sparta.binplay.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StreamRepository extends JpaRepository<Streams, Long> {
    Optional<Streams> findByUserAndVideo(Users user, Videos video);
    //Optional<Streams> findByUserIdAndVideoId(Long userId, Long videoId);

    @Query("SELECT av.video, COUNT(av), SUM(av.playTime) FROM Streams av WHERE av.createdAt >= :startOfDay AND av.createdAt < :endOfDay GROUP BY av.video")
    List<Object[]> countViewsAndPlayTime(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

}
