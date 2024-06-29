package com.eevan.bankingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Client's email DTO")
@Getter
@Setter
public class ClientEmailDTO {
    @Schema(description = "Client's email")
    @NotEmpty(message = "Email should not be empty")
    private String emailMain;

    @Schema(description = "Client's additional email if exists. Not necessary field")
    private String emailAdditional;
}
