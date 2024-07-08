package com.sparta.binplay.repository;

import com.sparta.binplay.entity.StatisticVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticVideoRepository extends JpaRepository<StatisticVideo, Long> {
    List<StatisticVideo> findByVideoVideoId(Long videoId);
}
