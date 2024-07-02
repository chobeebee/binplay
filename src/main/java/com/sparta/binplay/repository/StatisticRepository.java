package com.sparta.binplay.repository;

import com.sparta.binplay.dto.response.StatisticResponseDto;
import com.sparta.binplay.entity.Statistics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Statistics, Long> {
    List<Statistics> findByVideoVideoId(Long videoId);
    @Query("SELECT s FROM Statistics s WHERE s.period = :period ORDER BY s.periodViews DESC")
    List<StatisticResponseDto> findTop5ByPeriod(@Param("period") String period, Pageable pageable);
}
