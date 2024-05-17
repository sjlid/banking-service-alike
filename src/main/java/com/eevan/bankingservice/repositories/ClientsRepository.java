package com.eevan.bankingservice.repositories;

import com.eevan.bankingservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {
}
