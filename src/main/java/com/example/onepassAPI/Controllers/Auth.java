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
@RequestMapping("auth")
public class Auth {

    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @Autowired(required = false)
    private UserRepository userRepository;

    @PostMapping("/login")
    private String login(
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password
    ) throws JsonProcessingException {
        Response response = new Response();

        try {
            if (tokenRepository.existsByUid(userRepository.findByLogin(login).getId())) {
                Token token = tokenRepository.findByUid(userRepository.findByLogin(login).getId());
                tokenRepository.delete(token);
            }

            if (!userRepository.existsByLogin(login)) {
                response.setStatus(404);
                response.setData("user does not exist");
                return response.toJsonString();
            }

            User user = userRepository.findByLogin(login);
            if (!password.equals(user.getPasswordHASH())) {
                response.setStatus(404);
                response.setData("password not correct");
                return response.toJsonString();
            }

            Token token = new Token(user.getId());
            tokenRepository.save(token);

            response.setStatus(200);
            response.setData(token);
        }
        catch (Exception e) {
            response.setStatus(404);
            response.setData("error - " + e);
        }
        finally {
            return response.toJsonString();
        }
    }

    @PostMapping("/registration")
    private String registration(@RequestBody User user) throws JsonProcessingException {
        Response response = new Response();

        try {
            if (userRepository.existsByLogin(user.getLogin())) {
                response.setStatus(404);
                response.setData("Login already exist");
            }
            else {
                userRepository.save(user);

                Token token = new Token(user.getId());
                tokenRepository.save(token);

                response.setStatus(200);
                response.setData(token);
            }
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

    @PostMapping("/logout")
    private String logout(@RequestParam(value = "token") String token) throws JsonProcessingException {
        Response response = new Response();

        try {
            if (tokenRepository.existsByToken(token)) {
                Token tokenToDell = tokenRepository.findByToken(token);
                tokenRepository.delete(tokenToDell);

                response.setStatus(200);
                response.setData("ok");
            }
            else {
                response.setStatus(404);
                response.setData("error on delete");
            }
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
