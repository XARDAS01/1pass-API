package com.example.onepassAPI.Repositories;

import com.example.onepassAPI.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findById(long id);

    User findByLogin(String login);

    Boolean existsByLogin(String login);

}
