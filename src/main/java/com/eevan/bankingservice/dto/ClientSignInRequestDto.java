package com.eevan.bankingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "Client's signing in DTO")
@Data
public class ClientSignInRequestDto {
    @Schema(description = "User's login")
    @NotEmpty(message = "Login should not be empty")
    private String login;

    @Schema(description = "User's password")
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
