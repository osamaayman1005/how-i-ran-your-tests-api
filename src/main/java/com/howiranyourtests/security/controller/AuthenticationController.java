package com.howiranyourtests.security.controller;

import com.howiranyourtests.security.dto.AuthenticationResponse;
import com.howiranyourtests.security.dto.LoginDto;
import com.howiranyourtests.security.dto.RegisterDto;
import com.howiranyourtests.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterDto dto
    ){
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginDto dto
    ){
        return ResponseEntity.ok(authService.login(dto));    }

}
