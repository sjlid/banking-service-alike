package com.eevan.bankingservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientPhoneDTO {
    @NotEmpty(message = "Phone should not be empty")
    private String phoneNumberMain;
    private String phoneNumberAdditional;
}
