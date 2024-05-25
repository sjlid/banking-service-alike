package com.eevan.bankingservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.observation.ObservationFilter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotEmpty
    @Column(name = "username")
    private String username;

    @NotEmpty
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

    @NotEmpty(message = "Date of birth should not be empty")
    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Phone should not be empty")
    @Column(name = "phone_main", unique = true)
    private int phoneNumberMain;

    @Column(name = "phone_additional", unique = true)
    private int phoneNumberAdditional;

    @NotEmpty(message = "Email should not be empty")
    @Column(name = "email_main", unique = true)
    private String emailMain;

    @Column(name = "email_additional", unique = true)
    private String emailAdditional;

    @OneToOne
    @JoinColumn(name = "account_id")
    private ClientBankAccount clientBankAccount;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public Client(String name, String surname, String patronymic, LocalDate dateOfBirth, int phoneNumberMain, int phoneNumberAdditional, String emailMain, String emailAdditional, String username) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumberMain = phoneNumberMain;
        this.phoneNumberAdditional = phoneNumberAdditional;
        this.emailMain = emailMain;
        this.emailAdditional = emailAdditional;
        this.username = username;
    }

    public Client() {
    }
}
