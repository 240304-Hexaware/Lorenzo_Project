package com.revature.services;


import com.revature.authentication.AuthenticationRequest;
import com.revature.authentication.AuthenticationResponse;
import com.revature.authentication.RegisterRequest;
import com.revature.entity.User;
import com.revature.enums.UserRole;
import com.revature.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){
        var user =  User.builder()
                .username(request.getUsername()) // Set username from request
                .role(UserRole.USER)
                .email(request.getEmail()) // Set email from request
                .isAccountDisabled(request.getIsAccountDisabled())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        if (request.getUsername() == null || request.getPassword() == null) {
//            throw new IllegalArgumentException("Username and password cannot be null");
//        }
//
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                request.getUsername(),
//                request.getPassword()
//        ));
//        var user = repository.findByUsername(request.getUsername())
//                .orElseThrow();
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }

        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

//    public AuthenticationResponse authenticate(AuthenticationRequest request){
//        var user = repository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();
//    }

}
