package com.example.WebApp.repository;

import com.example.WebApp.domain.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    UserDetails findByUsername(String username);
}
