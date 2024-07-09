package com.sybyl.apis.demoapi.service;

import com.sybyl.apis.demoapi.config.JwtService;
import com.sybyl.apis.demoapi.model.ExpenseUser;
import com.sybyl.apis.demoapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String login(ExpenseUser request) {
        System.out.println("AuthenticationService.login");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return jwtToken.toString();
    }
}
