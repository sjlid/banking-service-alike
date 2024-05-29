package com.eevan.bankingservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ClientDTO {

    @NotEmpty(message = "Login should not be empty")
    private String login;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    private String surname;
    private String patronymic;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Phone should not be empty")
    private String phoneNumberMain;
    private String phoneNumberAdditional;

    @NotEmpty(message = "Email should not be empty")
    private String emailMain;
    private String emailAdditional;

    @NotEmpty(message = "Funds should not be empty")
    private BigDecimal funds;
}
