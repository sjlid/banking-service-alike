package com.eevan.bankingservice.repositories;

import com.eevan.bankingservice.entities.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmailMain(String email);
    Optional<Client> findByPhoneNumberMainOrPhoneNumberAdditional(String phoneNumber1, String phoneNumber2);
    Optional<List<Client>> findByNameLikeAndSurnameLikeAndPatronymicLikeAllIgnoreCase(String surname, String name, String patronymic, Pageable pageable);
    Optional<List<Client>> findByDateOfBirthAfter(LocalDate birthdate, Pageable pageable);
    Optional<Client> findById(long id);
}
