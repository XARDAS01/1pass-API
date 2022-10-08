package com.example.onepassAPI.Repositories;

import com.example.onepassAPI.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User findById(long id);

    User findByLogin(String login);

    User findByUid(String uid);

    ArrayList<User> findAll();
}
