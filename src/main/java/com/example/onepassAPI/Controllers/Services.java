package com.example.onepassAPI.Controllers;

import com.example.onepassAPI.Models.Response;
import com.example.onepassAPI.Models.Service;
import com.example.onepassAPI.Repositories.ServiceRepository;
import com.example.onepassAPI.Repositories.TokenRepository;
import com.example.onepassAPI.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("service")
public class Services {

    @Autowired(required = false)
    private ServiceRepository serviceRepository;

    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @PostMapping("/create")
    private String create(
            @RequestParam(value = "token") String token,
            @RequestBody Service service
    ) throws JsonProcessingException {
        Response response = new Response();

        try {
            if (!tokenRepository.existsByToken(token)) {
                response.setStatus(404);
                response.setData("bad token");
            }
            service.setUid(tokenRepository.findByToken(token).getUid());
            serviceRepository.save(service);

            response.setStatus(200);
            response.setData(service.getId());
        }
        catch (Exception e) {
            System.out.println(e);
            response.setStatus(404);
            response.setData("Error");
        }
        finally {
            return response.toJsonString();
        }
    }

    @GetMapping("/getAll")
    private String getAll(
            @RequestParam(value = "token") String token
    ) throws JsonProcessingException {
        Response response = new Response();

        try {
            if (!tokenRepository.existsByToken(token)) {
                response.setStatus(404);
                response.setData("bad token");
            }

            ArrayList<Service> services = serviceRepository.findAllByUid(tokenRepository.findByToken(token).getUid());

            response.setStatus(200);
            response.setData(services);
        }
        catch (Exception e) {
            System.out.println(e);
            response.setStatus(404);
            response.setData("Error");
        }
        finally {
            return response.toJsonString();
        }
    }

    @GetMapping("/getById")
    private String getById(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "id") long id
    ) throws JsonProcessingException {
        Response response = new Response();

        try {
            if (!tokenRepository.existsByToken(token)) {
                response.setStatus(404);
                response.setData("bad token");
            }

            if (!serviceRepository.existsById(id)) {
                response.setStatus(404);
                response.setData("bad id service");
            }

            Service serivce = serviceRepository.findById(id);

            if (!serivce.getUid().equals(tokenRepository.findByToken(token).getUid())) {
                response.setStatus(404);
                response.setData("bad request, uid not correct");
            }

            response.setStatus(200);
            response.setData(serivce);
        }
        catch (Exception e) {
            System.out.println(e);
            response.setStatus(404);
            response.setData("Error");
        }
        finally {
            return response.toJsonString();
        }
    }

    @PostMapping("/deleteById")
    private String deleteById(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "id") long id
    ) throws JsonProcessingException {
        Response response = new Response();

        try {
            if (!tokenRepository.existsByToken(token)) {
                response.setStatus(404);
                response.setData("bad token");
            }

            if (!serviceRepository.existsById(id)) {
                response.setStatus(404);
                response.setData("bad id service");
            }

            Service serivce = serviceRepository.findById(id);

            if (!serivce.getUid().equals(tokenRepository.findByToken(token).getUid())) {
                response.setStatus(404);
                response.setData("bad request, uid not correct");
            }

            serviceRepository.deleteById(id);
            response.setStatus(200);
            response.setData("ok");
        }
        catch (Exception e) {
            System.out.println(e);
            response.setStatus(404);
            response.setData("Error");
        }
        finally {
            return response.toJsonString();
        }
    }

}
