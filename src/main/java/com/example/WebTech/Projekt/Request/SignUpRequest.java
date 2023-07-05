package com.example.WebTech.Projekt.Request;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SignUpRequest {

    String email;
    String password;
    String lastname;
    String firstname;

    public String getEmail() {
        return decodeBase64(email);
    }

    public String getPassword() {
        return decodeBase64(password);
    }

    public String getLastname() {
        return decodeBase64(lastname);
    }

    public String getFirstname() {
        return decodeBase64(firstname);
    }



    public static String decodeBase64(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
