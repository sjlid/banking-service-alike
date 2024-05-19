package com.eevan.bankingservice.entities;

import jakarta.persistence.*;
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

    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Column(name = "surname", length = 32, nullable = false)
    private String surname;

    @Column(name = "patronymic", length = 32, nullable = false)
    private String patronymic;

    @Column(name = "birth_date", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "phone_main", unique = true, length = 32, nullable = false)
    private int phoneNumberMain;

    @Column(name = "phone_additional", unique = true, length = 32)
    private int phoneNumberAdditional;

    @Column(name = "email_main", unique = true, nullable = false)
    private String emailMain;

    @Column(name = "email_additional", unique = true)
    private String emailAdditional;

    @OneToOne
    @JoinColumn(name = "account_id")
    private ClientBankAccount clientBankAccount;

    public Client(String name, String surname, String patronymic, LocalDate dateOfBirth, int phoneNumberMain, int phoneNumberAdditional, String emailMain, String emailAdditional) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumberMain = phoneNumberMain;
        this.phoneNumberAdditional = phoneNumberAdditional;
        this.emailMain = emailMain;
        this.emailAdditional = emailAdditional;
    }

    public Client() {
    }
}
