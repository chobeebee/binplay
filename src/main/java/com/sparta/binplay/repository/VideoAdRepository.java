package com.sparta.binplay.repository;

import com.sparta.binplay.entity.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VideoAdRepository extends JpaRepository<VideoAd, Long> {
}
