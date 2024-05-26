package com.eevan.bankingservice.services;

import com.eevan.bankingservice.entities.Client;
import com.eevan.bankingservice.repositories.ClientsRepository;
import com.eevan.bankingservice.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ClientDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientsRepository clientsRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClientDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Entering in loadUserByUsername Method...");
        Client client = clientsRepository.findByUsername(username);
        if(client == null){
            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        logger.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(client);
    }
}
