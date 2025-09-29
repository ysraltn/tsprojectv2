package tech.ysraltn.tsprojectV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.ysraltn.tsprojectV2.dto.LoginRequest;
import tech.ysraltn.tsprojectV2.dto.RegisterRequest;
import tech.ysraltn.tsprojectV2.dto.JwtResponse;
import tech.ysraltn.tsprojectV2.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {
    "http://localhost:3000",
    "http://127.0.0.1:3000", 
    "http://192.168.1.115:3000",
    "http://35.226.27.83:8080"
}, allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    // @PostMapping("/register")
    // public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
    //     authService.register(request);
    //     return ResponseEntity.ok("User registered successfully");
    // }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        JwtResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
