package com.example.onepassAPI.Models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {

    private int status;
    private Object data;

    public Response() { }

    public Response(int status) {
        this.status = status;
    }

    public Response(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String toJsonString() throws JsonProcessingException {
        String responseJSON;
        final ObjectMapper objectMapper = new ObjectMapper();
        responseJSON = objectMapper.writeValueAsString(this);
        return responseJSON;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
