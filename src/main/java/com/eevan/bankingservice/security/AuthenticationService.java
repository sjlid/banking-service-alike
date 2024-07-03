package com.eevan.bankingservice.security;

import com.eevan.bankingservice.dto.ClientSignInRequestDto;
import com.eevan.bankingservice.dto.ClientSignUpRequestDto;
import com.eevan.bankingservice.entities.Client;
import com.eevan.bankingservice.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.eevan.bankingservice.dto.JwtAuthenticationResponseDto;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ClientService clientService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponseDto signUp(ClientSignUpRequestDto request) {

        var client = Client.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .surname(request.getSurname())
                .patronymic(request.getPatronymic())
                .emailMain(request.getEmailMain())
                .phoneNumberMain(request.getPhoneNumberMain())
                .dateOfBirth(request.getDateOfBirth())
                .initialBalance(request.getInitialBalance())
                .build();

        clientService.save(client);

        var jwt = jwtService.generateToken(client);
        return new JwtAuthenticationResponseDto(jwt);
    }

    public JwtAuthenticationResponseDto signIn(ClientSignInRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));

        var user = clientService
                .userDetailsService()
                .loadUserByUsername(request.getLogin());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDto(jwt);
    }

}
