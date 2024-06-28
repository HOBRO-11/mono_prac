package com.demo.mono_prac.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.mono_prac.db.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByUserId(String userId);
}
