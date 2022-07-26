package com.example.onepassAPI.Controllers;

import com.example.onepassAPI.Models.Response;
import com.example.onepassAPI.Models.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class tests {

    @GetMapping("/")
    private String test() throws JsonProcessingException {
        Response response = new Response();
        response.setStatus(200);
        response.setData(new Token(2L));

        return response.toJsonString();
    }

}
