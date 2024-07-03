package com.sparta.binplay.repository;

import com.sparta.binplay.entity.Users;
import com.sparta.binplay.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Videos, Long> {
    List<Videos> findAll();


    List<Videos> findAllByUser(Users user);
//
//    List<Videos> findAllByUserId(Long userId);

    void deleteByVideoId(Long videoId);

    List<Videos> findByUserUserId(Long userId);

    Optional<Videos> findByVideoId(Long videoId);
}
