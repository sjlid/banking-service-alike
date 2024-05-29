package com.eevan.bankingservice.controllers;

import com.eevan.bankingservice.dto.ClientDTO;
import com.eevan.bankingservice.dto.ClientEmailDTO;
import com.eevan.bankingservice.dto.ClientPhoneDTO;
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
import java.util.stream.Collectors;

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

    @PostMapping("/client")
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

    @PutMapping("/client/{id}/main_phone")
    public ResponseEntity<HttpStatus> changeMainPhone(@PathVariable int id, @RequestBody @Valid ClientPhoneDTO clientPhoneDTO,
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
        clientService.changeMainPhone(id, clientPhoneDTO.getPhoneNumberMain());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/client/{id}/main_email")
    public ResponseEntity<HttpStatus> changeMainEmail(@PathVariable int id, @RequestBody @Valid ClientEmailDTO clientEmailDTO,
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
        clientService.changeMainEmail(id, clientEmailDTO.getEmailMain());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/client/{id}/second_phone")
    public ResponseEntity<HttpStatus> addAdditionalPhone(@PathVariable int id, @RequestBody @Valid ClientPhoneDTO clientPhoneDTO,
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
        clientService.addAdditionalPhone(id, clientPhoneDTO.getPhoneNumberAdditional());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/client/{id}/second_email")
    public ResponseEntity<HttpStatus> addAdditionalEmail(@PathVariable int id, @RequestBody @Valid ClientEmailDTO clientEmailDTO,
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
        clientService.addAdditionalEmail(id, clientEmailDTO.getEmailAdditional());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/client/{id}/cleared_phone")
    public ResponseEntity<HttpStatus> deleteAdditionalPhone(@PathVariable int id, @RequestBody @Valid ClientPhoneDTO clientPhoneDTO,
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
        clientService.deleteAdditionalPhone(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/client/{id}/cleared_email")
    public ResponseEntity<HttpStatus> deleteAdditionalEmail(@PathVariable int id, @RequestBody @Valid ClientEmailDTO clientEmailDTO,
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
        clientService.deleteAdditionalEmail(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/clients/email")
    public ClientDTO getClientsByEmail(@RequestParam String email) {
        return convertToClientDTO(clientService.findClientByEmail(email));
    }

    @GetMapping("/clients/birthdate")
    public List<ClientDTO> getClientsByBirthdate(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate birthdate) {
        return clientService.findClientByBirthdate(birthdate)
                .stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/clients/phone")
    public ClientDTO getClientsByPhone(@RequestParam String phoneNumber) {
        return convertToClientDTO(clientService.findClientByPhone(phoneNumber));
    }

    @GetMapping("/clients/person")
    public List<ClientDTO> getClientsByFIO(@RequestParam String name,@RequestParam String surname,@RequestParam String patronymic) {
        return clientService.findClientByFIO(name, surname, patronymic)
                .stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList());
    }

    private Client convertToClient(ClientDTO clientDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(clientDTO, Client.class);
    }

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
