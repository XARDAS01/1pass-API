package com.example.onepassAPI.Repositories;

import com.example.onepassAPI.Models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ServicesRepository extends JpaRepository<Service, Long> {
    @Query("select u from Service u where u.name = :name and u.uid = :uid")
    Service findByNameAndUid(@Param("name") String name, @Param("uid") String uid);

    @Query("select u from Service u where u.id = :id and u.uid = :uid")
    Service findByIdAndUid(@Param("id") long id, @Param("uid") String uid);

    Service findById(long id);

    ArrayList<Service> findAllByUid(String uid);
}
