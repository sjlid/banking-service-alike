package com.eevan.bankingservice.repositories;

import com.eevan.bankingservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {
    Client findByEmailMain(String email);
    Client findByPhoneNumberMain(int number);
    Client findByNameAndSurnameAndPatronymic(String fio);
    Client findByDateOfBirthAfter(LocalDate birthdate);

}
