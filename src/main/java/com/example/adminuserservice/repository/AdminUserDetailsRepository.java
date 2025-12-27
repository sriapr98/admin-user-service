package com.example.adminuserservice.repository;

import com.example.adminuserservice.entity.AdminUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserDetailsRepository extends JpaRepository<AdminUserDetails, Long> {

    Optional<AdminUserDetails> findByUserId(String userId);
}
