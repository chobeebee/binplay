package com.sparta.binplay.repository;

import com.sparta.binplay.entity.Ads;
import com.sparta.binplay.entity.VideoAd;
import com.sparta.binplay.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VideoAdRepository extends JpaRepository<VideoAd, Long> {
    Optional<VideoAd> findByVideoAndAd(Videos video, Ads ad);
}
