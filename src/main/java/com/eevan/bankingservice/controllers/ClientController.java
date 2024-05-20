package com.eevan.bankingservice.controllers;

import com.eevan.bankingservice.entities.Client;
import com.eevan.bankingservice.repositories.ClientsRepository;
import com.eevan.bankingservice.services.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //добавление клиента
    @PostMapping("/technical/add")
    public ResponseEntity<HttpStatus> addClient(@RequestBody @Valid Client client,
                                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            // TODO
        }
        clientService.save(client);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //поиск клиента по емейлу
    @GetMapping("/clients/{email}")
    public Client getClientsByEmail(@PathVariable("email") String email) {
        return clientService.findClientByEmail(email);
    }

    //поиск клиента по дате рождения
    @GetMapping("/clients/{birthdate}")
    public Client getClientsByBirthdate(@PathVariable("birthdate") LocalDate birthdate) {
        return clientService.findClientByBirthdate(birthdate);
    }

    //поиск клиента по телефону
    @GetMapping("/clients/{phone}")
    public Client getClientsByPhone(@PathVariable("phone") int phone) {
        return clientService.findClientByPhone(phone);
    }

    //поиск клиента по ФИО
    @GetMapping("/clients/{fio}")
    public Client getClientsByFIO(@PathVariable("fio") String fio) {
        return clientService.findClientByFIO(fio);
    }
}
