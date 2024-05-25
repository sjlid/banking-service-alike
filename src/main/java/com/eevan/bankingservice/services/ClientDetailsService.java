package com.eevan.bankingservice.services;

import com.eevan.bankingservice.entities.Client;
import com.eevan.bankingservice.repositories.ClientsRepository;
import com.eevan.bankingservice.security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientDetailsService implements UserDetailsService {
    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientDetailsService(ClientsRepository employeeRepository) {
        this.clientsRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = clientsRepository.findByUsername(username);

        if (client.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new ClientDetails(client.get());
    }
}
