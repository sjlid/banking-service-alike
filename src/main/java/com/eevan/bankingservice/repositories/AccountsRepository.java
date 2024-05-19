package com.eevan.bankingservice.repositories;

import com.eevan.bankingservice.entities.ClientBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<ClientBankAccount, Integer> {
}
