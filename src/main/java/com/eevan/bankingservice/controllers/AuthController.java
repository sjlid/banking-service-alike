package com.eevan.bankingservice.controllers;

import com.eevan.bankingservice.dto.ClientSignInRequestDto;
import com.eevan.bankingservice.dto.ClientSignUpRequestDto;
import com.eevan.bankingservice.dto.JwtAuthenticationResponseDto;
import com.eevan.bankingservice.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Client registration")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponseDto signUp(@RequestBody @Valid ClientSignUpRequestDto request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Client authorization")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponseDto signIn(@RequestBody @Valid ClientSignInRequestDto request) {
        return authenticationService.signIn(request);
    }
}
