package com.sparta.binplay.repository;


import com.sparta.binplay.entity.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ads, Long> {
}
