package com.example.onepassAPI.Controllers;

import com.example.onepassAPI.Models.User;
import com.example.onepassAPI.Payloads.response.MessageResponse;
import com.example.onepassAPI.Repositories.TokenRepository;
import com.example.onepassAPI.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/users"})
public class UsersController {

    @Autowired(required = false)
    private UsersRepository usersRepository;

    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @GetMapping
    private MessageResponse getUser(@RequestBody String token) {
        try {
            if (tokenRepository.findByToken(token) != null) {
                User user = usersRepository.findByUid(tokenRepository.findByToken(token).getUid());
                return new MessageResponse("ok", 200, user);
            } else { return new MessageResponse("token is not found", 404); }
        } catch (Exception e) { return new MessageResponse(e.toString(), 400); }
    }
}
