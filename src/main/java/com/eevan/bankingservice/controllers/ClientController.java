package com.eevan.bankingservice.controllers;

import com.eevan.bankingservice.dto.ClientDTO;
import com.eevan.bankingservice.entities.Client;
import com.eevan.bankingservice.services.ClientService;
import com.eevan.bankingservice.utils.ClientErrorResponse;
import com.eevan.bankingservice.utils.ClientNotCreatedException;
import com.eevan.bankingservice.utils.ClientNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }




    //добавление клиента
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addClient(@RequestBody @Valid ClientDTO clientDTO,
                                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(" ; ");
            }
            throw new ClientNotCreatedException(errorMessage.toString());
        }
        clientService.save(convertToClient(clientDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //поиск клиента по емейлу
    @GetMapping("/clients/email")
    public ClientDTO getClientsByEmail(@RequestParam String email) {
        return convertToClientDTO(clientService.findClientByEmail(email));
    }

    //поиск клиента по дате рождения
    @GetMapping("/clients/birthdate")
    public List<Client> getClientsByBirthdate(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate birthdate) {
        return clientService.findClientByBirthdate(birthdate);
    }

    //поиск клиента по телефону
    @GetMapping("/clients/phone")
    public Client getClientsByPhone(@RequestParam String phoneNumber) {
        return clientService.findClientByPhone(phoneNumber);
    }

    //поиск клиента по ФИО
    @GetMapping("/clients/person")
    public List<Client> getClientsByFIO(@RequestParam String name,@RequestParam String surname,@RequestParam String patronymic) {
        return clientService.findClientByFIO(name, surname, patronymic);
    }

    //маппинг из дто в объект
    private Client convertToClient(ClientDTO clientDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(clientDTO, Client.class);
    }

    //маппинг из объекта в дто
    private ClientDTO convertToClientDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotFoundException e) {
        ClientErrorResponse response = new ClientErrorResponse("Client not found!", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotCreatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
