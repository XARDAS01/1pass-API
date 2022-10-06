package com.example.onepassAPI.Controllers;

import com.example.onepassAPI.Models.Service;
import com.example.onepassAPI.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("service")
public class ServiceController {

    @Autowired(required = false)
    private ServiceRepository serviceRepository;

    @PostMapping("/add")
    private String serviceAdd (@RequestBody Service service) {
        serviceRepository.save(service);
        return "ok";
    }

    @GetMapping("/getById")
    private Service getServiceById (@RequestBody long id) {
        Service service = serviceRepository.findById(id);
        return service;
    }

    @GetMapping("/getAll")
    private ArrayList<Service> getAll (@RequestBody long uid) {
        ArrayList<Service> services = serviceRepository.findAllByUid(uid);
        return services;
    }
}
