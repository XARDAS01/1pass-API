package com.example.onepassAPI.Repositories;

import com.example.onepassAPI.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByUid(String uid);

    Token findByToken(String token);
}
