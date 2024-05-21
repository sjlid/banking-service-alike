package com.eevan.bankingservice.services;

import com.eevan.bankingservice.entities.Client;
import com.eevan.bankingservice.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    //создание клиента
    @Transactional
    public void save(Client client) {
        clientsRepository.save(client);
    }

    //добавление телефона
    //добавление емейла
    //изменение телефона
    //изменение емейла
    //удаление телефона
    //удаление емейла

    //поиск клиента по дате рождения
    @Transactional(readOnly = true)
    public Client findClientByBirthdate() {
        return null;
    }

    //поиск клиента по телефону
    @Transactional(readOnly = true)
    public Client findClientByPhone(int number) {
        return clientsRepository.findByPhoneNumberMain(number);
    }

    //поиск клиента по емейлу
    @Transactional(readOnly = true)
    public Client findClientByEmail(String email) {
        return clientsRepository.findByEmailMain(email);
    }

    //поиск клиента по ФИО
    @Transactional(readOnly = true)
    public Client findClientByFIO(String fio) {
        return clientsRepository.findByNameAndSurnameAndPatronymic(fio);
    }

    @Transactional(readOnly = true)
    public Client findClientByBirthdate(LocalDate birthdate) {
        return clientsRepository.findByDateOfBirthAfter(birthdate);
    }

}
