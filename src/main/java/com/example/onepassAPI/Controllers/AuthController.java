package com.example.onepassAPI.Controllers;

import com.example.onepassAPI.Models.Auth;
import com.example.onepassAPI.Models.Token;
import com.example.onepassAPI.Models.User;
import com.example.onepassAPI.Payloads.request.AuthLoginRequest;
import com.example.onepassAPI.Payloads.request.AuthRegistrationRequest;
import com.example.onepassAPI.Payloads.response.MessageResponse;
import com.example.onepassAPI.Repositories.AuthRepository;
import com.example.onepassAPI.Repositories.TokenRepository;
import com.example.onepassAPI.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/auth"})
public class AuthController {

    @Autowired(required = false)
    private UsersRepository usersRepository;

    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @Autowired(required = false)
    private AuthRepository authRepository;

    @PostMapping("/registration")
    private MessageResponse registration(@RequestBody AuthRegistrationRequest authRegistrationRequest) {
        try {
            if (usersRepository.findByLogin(authRegistrationRequest.getLogin()) == null) {
                User user = new User(authRegistrationRequest);
                usersRepository.save(user);

                Auth auth = new Auth(user.getUid(), authRegistrationRequest);
                authRepository.save(auth);

                Token token = new Token(user.getUid());
                tokenRepository.save(token);
                return new MessageResponse("User registered", 200, token);
            }
            else { return new MessageResponse("Login already exist", 400);  }
        } catch (Exception e) { return new MessageResponse(e.toString(), 400); }
    }

    @PostMapping("/login")
    private MessageResponse login(@RequestBody AuthLoginRequest authLoginRequest) {
        try {
            if (authRepository.findByLogin(authLoginRequest.getLogin()) != null) {
                Auth auth = authRepository.findByLogin(authLoginRequest.getLogin());
                if (auth.getPassword().equals(authLoginRequest.getPassword())) {
                    tokenRepository.delete(tokenRepository.findByUid(auth.getUid()));
                    Token token = new Token(auth.getUid());
                    tokenRepository.save(token);
                    return new MessageResponse("ok", 400, token);
                } else { return new MessageResponse("password is not correct", 400); }
            } else { return new MessageResponse("user not found", 404); }
        } catch (Exception e) { return new MessageResponse(e.toString(), 400); }
    }
}