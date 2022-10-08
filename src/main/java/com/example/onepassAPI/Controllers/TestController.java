package com.example.onepassAPI.Controllers;

import com.example.onepassAPI.Models.User;
import com.example.onepassAPI.Payloads.response.MessageResponse;
import com.example.onepassAPI.Repositories.UsersRepository;
import com.example.onepassAPI.Services.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @Autowired(required = false)
    private UsersRepository usersRepository;

    @GetMapping("/")
    private MessageResponse test () {
        String uid = GeneratorService.initUID();
        User user = usersRepository.findByLogin("login");
        return new MessageResponse("ok", 200, user);
    }
}
