package com.eevan.bankingservice.services;

import com.eevan.bankingservice.dto.PhoneDTO;
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

    //изменение основного телефона
    @Transactional
    public void changeMainPhone(int id, String phoneNumber) {
        Client updatedClient = findClientById(id);
        updatedClient.setPhoneNumberMain(phoneNumber);
        clientsRepository.save(updatedClient);
    }

    //изменение основного емейла
    @Transactional
    public void changeMainEmail(int id, String email) {
        Client updatedClient = findClientById(id);
        updatedClient.setEmailMain(email);
        clientsRepository.save(updatedClient);
    }

    //добавление/изменение второго телефона
    @Transactional
    public void addAdditionalPhone(int id, String phoneNumber) {
        Client updatedClient = findClientById(id);
        updatedClient.setPhoneNumberAdditional(phoneNumber);
        clientsRepository.save(updatedClient);
    }

    //добавление/изменение второго емейла
    @Transactional
    public void addAdditionalEmail(int id, String email) {
        Client updatedClient = findClientById(id);
        updatedClient.setEmailAdditional(email);
        clientsRepository.save(updatedClient);
    }

    //удаление второго телефона
    @Transactional
    public void deleteAdditionalPhone(int id) {
        Client updatedClient = findClientById(id);
        updatedClient.setPhoneNumberAdditional(null);
        clientsRepository.save(updatedClient);
    }

    //удаление второго емейла
    @Transactional
    public void deleteAdditionalEmail(int id) {
        Client updatedClient = findClientById(id);
        updatedClient.setEmailAdditional(null);
        clientsRepository.save(updatedClient);
    }

    //поиск клиентов по дате рождения
    @Transactional(readOnly = true)
    public List<Client> findClientByBirthdate(LocalDate birthdate) {
        Optional<List<Client>> foundClients = clientsRepository.findByDateOfBirthAfter(birthdate);
        return foundClients.orElseThrow(ClientNotFoundException::new);
    }

    //поиск клиента по телефону
    @Transactional(readOnly = true)
    public Client findClientByPhone(String phoneNumber) {
        Optional<Client> foundClient = clientsRepository.findByPhoneNumberMainOrPhoneNumberAdditional(phoneNumber, phoneNumber);
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
    public List<Client> findClientByFIO(String surname, String name, String patronymic) {
        Optional<List<Client>> foundClients = clientsRepository.findByNameLikeAndSurnameLikeAndPatronymicLikeAllIgnoreCase(surname, name, patronymic);
        return foundClients.orElseThrow(ClientNotFoundException::new);
    }

    //поиск клиента по айди
    @Transactional(readOnly = true)
    public Client findClientById(int id) {
        Optional<Client> foundClient = clientsRepository.findById(id);
        return foundClient.orElseThrow(ClientNotFoundException::new);
    }
}
