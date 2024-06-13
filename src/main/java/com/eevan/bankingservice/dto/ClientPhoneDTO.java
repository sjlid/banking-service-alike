package com.eevan.bankingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Client's phone DTO")
@Getter
@Setter
public class ClientPhoneDTO {
    @NotEmpty(message = "Phone should not be empty")
    private String phoneNumberMain;
    private String phoneNumberAdditional;
}
