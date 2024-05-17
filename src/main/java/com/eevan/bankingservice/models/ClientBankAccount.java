package com.eevan.bankingservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
public class ClientBankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long accountId;

    @Column(name = "funds")
    private BigDecimal funds;

    @Column(name = "client_id")
    private int clientId;
}
