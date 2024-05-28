package com.eevan.bankingservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
    @NotEmpty(message = "Email should not be empty")
    private String emailMain;
    private String emailAdditional;
}
