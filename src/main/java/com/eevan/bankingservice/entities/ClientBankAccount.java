package com.eevan.bankingservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
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

    public ClientBankAccount(BigDecimal funds, int clientId) {
        this.funds = funds;
        this.clientId = clientId;
    }

    public ClientBankAccount() {
    }
}
