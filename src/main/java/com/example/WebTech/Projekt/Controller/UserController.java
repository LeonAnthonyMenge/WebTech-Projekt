package com.example.WebTech.Projekt.Controller;

import com.example.WebTech.Projekt.Request.LoginRequest;
import com.example.WebTech.Projekt.Request.SignUpRequest;
import com.example.WebTech.Projekt.User.LoginResponse;
import com.example.WebTech.Projekt.User.User;
import com.example.WebTech.Projekt.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        if (user != null && user.isPasswordValid(loginRequest.getPassword())) {
            LoginResponse response = new LoginResponse(true, "Login successful", user.getId());
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse(false, "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/api/registration")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        try{
            User user = new User(signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getPassword(), signUpRequest.getEmail());
            userService.save(user);
            User newUser = userService.findByEmail(user.getEmail());
            LoginResponse response = new LoginResponse(true, "Registration successful", newUser.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e){
            LoginResponse response = new LoginResponse(false, "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/api/getUser")
    public ResponseEntity<User> getUser(@RequestParam("email") String email) {
        String decodedEmail = LoginRequest.decodeBase64(email);
        User user = userService.findByEmail(decodedEmail);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "Delete by id";
    }

}
