package com.eevan.bankingservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "phone_main")
    private int phoneNumberMain;

    @Column(name = "phone_additional")
    private int phoneNumberAdditional;

    @Column(name = "email_main")
    private String emailMain;

    @Column(name = "email_additional")
    private String emailAdditional;

    @OneToOne
    @JoinColumn(name = "account_id")
    private ClientBankAccount clientBankAccount;
}
