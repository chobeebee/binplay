package com.sparta.binplay.repository;

import com.sparta.binplay.entity.StatisticAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticAdRepository extends JpaRepository<StatisticAd, Long> {
}
