package com.example.WebTech.Projekt.Controller;

import com.example.WebTech.Projekt.Request.LoginRequest;
import com.example.WebTech.Projekt.User.LoginResponse;
import com.example.WebTech.Projekt.User.User;
import com.example.WebTech.Projekt.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        boolean isPasswordValid = user.isPasswordValid(loginRequest.getPassword());

        if (isPasswordValid) {
            LoginResponse response = new LoginResponse(true, "Login successful");
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse(false, "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/api/registrate")
    public ResponseEntity<?> signup(@RequestBody User user){
        try{
            userService.save(user);
            LoginResponse response = new LoginResponse(true, "Registration successful");
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Erstellen des Benutzers: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "Delete by id";
    }

}
