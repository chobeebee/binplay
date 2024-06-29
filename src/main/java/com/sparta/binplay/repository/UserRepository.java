package com.sparta.binplay.repository;

import com.sparta.binplay.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    //Users findByEmail(String email);

    Users findByEmail(String email);

}
