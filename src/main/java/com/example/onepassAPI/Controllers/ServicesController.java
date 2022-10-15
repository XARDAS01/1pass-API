package com.example.onepassAPI.Controllers;

import com.example.onepassAPI.Models.Service;
import com.example.onepassAPI.Payloads.response.MessageResponse;
import com.example.onepassAPI.Repositories.ServicesRepository;
import com.example.onepassAPI.Repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/services"})
public class ServicesController {

    @Autowired(required = false)
    private ServicesRepository servicesRepository;

    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @PostMapping
    private MessageResponse addService(
            @RequestParam(value = "token") String token,
            @RequestBody Service serviceToSave
    ) {
        try {
            if (tokenRepository.findByToken(token) != null) {
                Service service = serviceToSave;
                service.setUid(tokenRepository.findByToken(token).getUid());
                servicesRepository.save(service);
                return new MessageResponse("ok", 200);
            } else return new MessageResponse("token not found", 404);
        } catch (Exception e) { return new MessageResponse(e.toString(), 400); }
    }

    @GetMapping
    private MessageResponse getAll(@RequestParam (value = "token") String token) {
        try {
            if (tokenRepository.findByToken(token) != null) {
                ArrayList<Service> services = servicesRepository.findAllByUid(tokenRepository.findByToken(token).getUid());
                if (services != null) return new MessageResponse("ok", 200, services);
                else return new MessageResponse("services not found", 404);
            } else return new MessageResponse("token not found", 404);
        } catch (Exception e) { return new MessageResponse(e.toString(), 400); }
    }

    @GetMapping("/{name}")
    private MessageResponse getByName(
            @RequestBody String token,
            @PathVariable("name") String name
    ) {
        try {
            if (tokenRepository.findByToken(token) != null) {
                Service service = servicesRepository.findByNameAndUid(name, tokenRepository.findByToken(token).getUid());
                if (service != null) return new MessageResponse("ok", 200, service);
                else return new MessageResponse("service not found", 404);
            } else return new MessageResponse("token not found", 404);
        } catch (Exception e) { return new MessageResponse(e.toString(), 400); }
    }

    @DeleteMapping("/{name}")
    private MessageResponse deleteByName(
            @RequestParam String token,
            @PathVariable("name") String name
    ) {
        try {
            if (tokenRepository.findByToken(token) != null) {
                servicesRepository.delete(servicesRepository.findByNameAndUid(name, tokenRepository.findByToken(token).getUid()));
                return new MessageResponse("ok", 200);
            } else return new MessageResponse("token not found", 404);
        } catch (Exception e) { return new MessageResponse(e.toString(), 400); }
    }
}
