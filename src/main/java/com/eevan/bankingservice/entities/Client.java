package com.eevan.bankingservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Clients")
public class Client implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Login should not be empty")
    @Column(name = "login")
    private String login;

    @NotEmpty(message = "Password should not be empty")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @NotNull(message = "Date of birth should not be empty")
    @Column(name = "birth_date")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Phone should not be empty")
    @Column(name = "phone_main", unique = true)
    private String phoneNumberMain;

    @Column(name = "phone_additional", unique = true)
    private String phoneNumberAdditional;

    @NotEmpty(message = "Email should not be empty")
    @Column(name = "email_main", unique = true)
    private String emailMain;

    @Column(name = "email_additional", unique = true)
    private String emailAdditional;

    @Positive(message = "Initial balance should be positive")
    @NotNull(message = "Initial balance should not be empty")
    @Column(name = "initial_balance")
    private Double initialBalance;

    @Column(name = "current_balance")
    private Double currentBalance;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
