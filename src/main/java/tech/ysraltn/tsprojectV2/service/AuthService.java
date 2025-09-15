package tech.ysraltn.tsprojectV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ysraltn.tsprojectV2.dto.JwtResponse;
import tech.ysraltn.tsprojectV2.dto.LoginRequest;
import tech.ysraltn.tsprojectV2.dto.RegisterRequest;
import tech.ysraltn.tsprojectV2.model.Role;
import tech.ysraltn.tsprojectV2.model.User;
import tech.ysraltn.tsprojectV2.repository.RoleRepository;
import tech.ysraltn.tsprojectV2.repository.UserRepository;
import tech.ysraltn.tsprojectV2.security.CustomUserDetails;
import tech.ysraltn.tsprojectV2.security.JwtService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {
        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(defaultRole))
                .build();

        userRepository.save(user);
    }

    public JwtResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwt = jwtService.generateToken(new CustomUserDetails(user));

        return new JwtResponse(jwt);
    }
}
