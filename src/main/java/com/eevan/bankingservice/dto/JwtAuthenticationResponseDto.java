package com.eevan.bankingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response with JWT")
public class JwtAuthenticationResponseDto {
    @Schema(description = "JWT")
    private String token;
}
