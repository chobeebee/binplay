package com.sparta.binplay.repository;

import com.sparta.binplay.entity.VideoAd;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoAdRepository extends JpaRepository<VideoAd, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM VideoAd v WHERE v.video.videoId = :videoId")
    void deleteByVideoId(@Param("videoId") Long videoId);
}
