package com.example.WebTech.Projekt.User;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class LoginResponse {

    private boolean success;
    private String message;

    private Long id;

    public LoginResponse(boolean success, String message, Long id) {
        this.success = success;
        this.message = message;
        this.id = id;
    }

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public LoginResponse(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
