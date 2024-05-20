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

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    @Column(name = "phone_main", unique = true)
    private int phoneNumberMain;

    @Column(name = "phone_additional", unique = true)
    private int phoneNumberAdditional;

    @Column(name = "email_main", unique = true)
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
