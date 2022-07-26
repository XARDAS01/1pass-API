package com.example.onepassAPI.Controllers;

import com.example.onepassAPI.Models.Response;
import com.example.onepassAPI.Models.Token;
import com.example.onepassAPI.Models.User;
import com.example.onepassAPI.Repositories.TokenRepository;
import com.example.onepassAPI.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("user")
public class Users {

    @Autowired(required = false)
    private UserRepository userRepository;

    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @GetMapping("/get")
    private String getUser(@RequestParam(value = "token") String token) throws JsonProcessingException {
        Response response = new Response();

        try {
            if (!tokenRepository.existsByToken(token)) {
                response.setStatus(404);
                response.setData("error: bad token");
                return response.toJsonString();
            }

            Token token1 = tokenRepository.findByToken(token);
            long uid = token1.getUid();
            User user = userRepository.findById(uid);

            response.setStatus(200);
            response.setData(user);
        }
        catch (Exception e) {
            System.out.println(e);
            response.setStatus(404);
            response.setData("error - " + e);
        }
        finally {
            return response.toJsonString();
        }
    }
}
