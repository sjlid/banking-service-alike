package com.eevan.bankingservice.repositories;

import com.eevan.bankingservice.entities.Client;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmailMain(String email);

    Optional<Client> findByPhoneNumberMainOrPhoneNumberAdditional(String phoneNumber1, String phoneNumber2);

    Optional<List<Client>> findByNameLikeAndSurnameLikeAndPatronymicLikeAllIgnoreCase(String surname,
                                                                                      String name,
                                                                                      String patronymic,
                                                                                      Pageable pageable);

    Optional<List<Client>> findByDateOfBirthAfter(LocalDate birthdate, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Client> findById(long id);

    Optional<Client> findByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsByPhoneNumberMainOrPhoneNumberAdditional(String phoneNumber1, String phoneNumber2);

    boolean existsByEmailMainOrEmailAdditional(String email1, String email2);

}
