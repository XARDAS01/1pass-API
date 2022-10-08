package com.example.onepassAPI.Repositories;

import com.example.onepassAPI.Models.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByLogin(String login);
}
