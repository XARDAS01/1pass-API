package com.example.onepassAPI.Repositories;

import com.example.onepassAPI.Models.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
    Service findById(long id);

    ArrayList<Service> findAllByUid(long uid);
}
