package com.example.onepassAPI.Repositories;

import com.example.onepassAPI.Models.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Token findById(long id);

    Token findByUid(long uid);

    Token findByToken(String token);

    Boolean existsByToken(String token);

    Boolean existsByUid(long uid);
}
