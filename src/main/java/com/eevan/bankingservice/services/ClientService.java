package com.eevan.bankingservice.services;

import com.eevan.bankingservice.entities.Client;
import com.eevan.bankingservice.repositories.ClientsRepository;
import com.eevan.bankingservice.utils.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Transactional
    public void save(Client client) {
        clientsRepository.save(client);
    }

    @Transactional
    public void changeMainPhone(int id, String phoneNumber) {
        Client updatedClient = findClientById(id);
        updatedClient.setPhoneNumberMain(phoneNumber);
        clientsRepository.save(updatedClient);
    }

    @Transactional
    public void changeMainEmail(int id, String email) {
        Client updatedClient = findClientById(id);
        updatedClient.setEmailMain(email);
        clientsRepository.save(updatedClient);
    }

    @Transactional
    public void addAdditionalPhone(int id, String phoneNumber) {
        Client updatedClient = findClientById(id);
        updatedClient.setPhoneNumberAdditional(phoneNumber);
        clientsRepository.save(updatedClient);
    }

    @Transactional
    public void addAdditionalEmail(int id, String email) {
        Client updatedClient = findClientById(id);
        updatedClient.setEmailAdditional(email);
        clientsRepository.save(updatedClient);
    }

    @Transactional
    public void deleteAdditionalPhone(int id) {
        Client updatedClient = findClientById(id);
        updatedClient.setPhoneNumberAdditional(null);
        clientsRepository.save(updatedClient);
    }

    @Transactional
    public void deleteAdditionalEmail(int id) {
        Client updatedClient = findClientById(id);
        updatedClient.setEmailAdditional(null);
        clientsRepository.save(updatedClient);
    }

    @Transactional(readOnly = true)
    public List<Client> findClientByBirthdate(LocalDate birthdate, int pageNo, int recordCount) {
        Pageable pageable = PageRequest.of(pageNo, recordCount);
        Optional<List<Client>> foundClients = clientsRepository.findByDateOfBirthAfter(birthdate, pageable);
        return foundClients.orElseThrow(ClientNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Client findClientByPhone(String phoneNumber) {
        Optional<Client> foundClient = clientsRepository.findByPhoneNumberMainOrPhoneNumberAdditional(phoneNumber, phoneNumber);
        return foundClient.orElseThrow(ClientNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Client findClientByEmail(String email) {
        Optional<Client> foundClient = clientsRepository.findByEmailMain(email);
        return foundClient.orElseThrow(ClientNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Client> findClientByFIO(String surname, String name, String patronymic,  int pageNo, int recordCount) {
        Pageable pageable = PageRequest.of(pageNo, recordCount);
        Optional<List<Client>> foundClients = clientsRepository.findByNameLikeAndSurnameLikeAndPatronymicLikeAllIgnoreCase(surname, name, patronymic, pageable);
        return foundClients.orElseThrow(ClientNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Client findClientById(int id) {
        Optional<Client> foundClient = clientsRepository.findById(id);
        return foundClient.orElseThrow(ClientNotFoundException::new);
    }
}
