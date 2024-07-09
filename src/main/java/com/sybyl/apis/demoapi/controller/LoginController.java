package com.sybyl.apis.demoapi.controller;

import com.sybyl.apis.demoapi.model.ExpenseUser;
import com.sybyl.apis.demoapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ExpenseUser user) {
        return ResponseEntity.ok(authenticationService.login(user));
    }
}