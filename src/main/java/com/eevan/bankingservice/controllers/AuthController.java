package com.eevan.bankingservice.controllers;

import com.eevan.bankingservice.dto.ClientSignInRequestDto;
import com.eevan.bankingservice.dto.ClientSignUpRequestDto;
import com.eevan.bankingservice.dto.JwtAuthenticationResponseDto;
import com.eevan.bankingservice.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create new client", description = "Here you can create a new client",
            tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client has created"),
            @ApiResponse(responseCode = "400", description = "Not all the necessary fields are filled"),
            @ApiResponse(responseCode = "500", description = "Not all the necessary fields are present in DTO or has correct values")
    })
    @PostMapping("/sign-up")
    public JwtAuthenticationResponseDto signUp(@RequestBody @Valid ClientSignUpRequestDto request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Login as client", description = "Here you can logging in as a client",
            tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client has created"),
            @ApiResponse(responseCode = "400", description = "Not all the necessary fields are filled"),
            @ApiResponse(responseCode = "500", description = "Not all the necessary fields are present in DTO or has correct values")
    })
    @PostMapping("/sign-in")
    public JwtAuthenticationResponseDto signIn(@RequestBody @Valid ClientSignInRequestDto request) {
        return authenticationService.signIn(request);
    }
}
