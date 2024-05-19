package com.eevan.bankingservice.services;

import com.eevan.bankingservice.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }
}
