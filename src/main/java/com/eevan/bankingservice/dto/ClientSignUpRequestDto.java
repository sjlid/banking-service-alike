package com.eevan.bankingservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Client's signing up DTO")
@Data
public class ClientSignUpRequestDto {
    @Schema(description = "User's login")
    @NotEmpty(message = "Login should not be empty")
    @Column(name = "login")
    private String login;

    @Schema(description = "User's password")
    @NotEmpty(message = "Password should not be empty")
    @Column(name = "password")
    private String password;

    @Schema(description = "Client's name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Schema(description = "Client's surname")
    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    private String surname;

    @Schema(description = "Client's patronymic if exists. Not necessary field")
    private String patronymic;

    @Schema(description = "Client's date of birth")
    @NotNull(message = "Date of birth should not be empty")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;

    @Schema(description = "Client's main phone number")
    @NotEmpty(message = "Phone should not be empty")
    private String phoneNumberMain;

    @Schema(description = "Client's email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be user@example.com alike")
    private String emailMain;

    @Schema(description = "Client's balance")
    @Positive(message = "Initial balance should be positive")
    @NotNull(message = "Initial balance should not be empty")
    private Double initialBalance;
}
