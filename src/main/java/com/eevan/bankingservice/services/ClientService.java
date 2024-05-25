package com.eevan.bankingservice.services;

import com.eevan.bankingservice.entities.Client;
import com.eevan.bankingservice.repositories.ClientsRepository;
import com.eevan.bankingservice.utils.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
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

    //поиск клиентов по дате рождения
    @Transactional(readOnly = true)
    public List<Client> findClientByBirthdate(LocalDate birthdate) {
        Optional<List<Client>> foundClients = clientsRepository.findByDateOfBirthAfter(birthdate);
        return foundClients.orElseThrow(ClientNotFoundException::new);
    }

    //поиск клиента по телефону
    @Transactional(readOnly = true)
    public Client findClientByPhone(int number) {
        Optional<Client> foundClient = clientsRepository.findByPhoneNumberMain(number);
        return foundClient.orElseThrow(ClientNotFoundException::new);
    }

    //поиск клиента по емейлу
    @Transactional(readOnly = true)
    public Client findClientByEmail(String email) {
        Optional<Client> foundClient = clientsRepository.findByEmailMain(email);
        return foundClient.orElseThrow(ClientNotFoundException::new);
    }

    //поиск клиента по ФИО
    @Transactional(readOnly = true)
    public List<Client> findClientByFIO(String name, String surname, String patronymic) {
        Optional<List<Client>> foundClients = clientsRepository.findByNameAndSurnameAndPatronymic(name, surname, patronymic);
        return foundClients.orElseThrow(ClientNotFoundException::new);
    }
}
