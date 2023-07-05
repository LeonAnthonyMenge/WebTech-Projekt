package com.example.WebTech.Projekt.Request;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class LoginRequest {

    String email;
    String password;

    public String getEmail() {
        String decodedEmail = decodeBase64(this.email);
        return decodedEmail;
    }

    public String getPassword() {
        String decodedPassword = decodeBase64(this.password);
        return decodedPassword;
    }

    //Decode encoded response
    public static String decodeBase64(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
