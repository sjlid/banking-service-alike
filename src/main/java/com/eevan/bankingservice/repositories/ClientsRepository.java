package com.eevan.bankingservice.repositories;

import com.eevan.bankingservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmailMain(String email);
    Optional<Client> findByPhoneNumberMainOrPhoneNumberAdditionalEquals(String number1, String number2);
    Optional<List<Client>> findByNameAndSurnameAndPatronymic(String surname, String name, String patronymic);
    Optional<List<Client>> findByDateOfBirthAfter(LocalDate birthdate);
}
