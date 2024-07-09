package com.eevan.bankingservice.controllers;

import com.eevan.bankingservice.dto.ClientDto;
import com.eevan.bankingservice.dto.ClientEmailDto;
import com.eevan.bankingservice.dto.ClientPhoneDto;
import com.eevan.bankingservice.entities.Client;
import com.eevan.bankingservice.services.ClientService;
import com.eevan.bankingservice.utils.ClientErrorResponse;
import com.eevan.bankingservice.utils.ClientNotCreatedException;
import com.eevan.bankingservice.utils.ClientNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Operation(summary = "Change a main phone number", description = "Here you can change a main number of client",
            tags = { "client" })
    @PutMapping("/client/{id}/main_phone")
    public ResponseEntity<HttpStatus> changeMainPhone(@PathVariable int id,
                                                      @RequestBody @Valid ClientPhoneDto clientPhoneDto
    ) {
        clientService.changeMainPhone(id, clientPhoneDto.getPhoneNumberMain());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Change a main email", description = "Here you can change a main email of client",
            tags = { "client" })
    @PutMapping("/client/{id}/main_email")
    public ResponseEntity<HttpStatus> changeMainEmail(@PathVariable int id,
                                                      @RequestBody @Valid ClientEmailDto clientEmailDto
    ) {
        clientService.changeMainEmail(id, clientEmailDto.getEmailMain());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Add an additional phone number", description = "Here you can add an additional phone number of client",
            tags = { "client" })
    @PutMapping("/client/{id}/second_phone")
    public ResponseEntity<HttpStatus> addAdditionalPhone(@PathVariable int id,
                                                         @RequestBody @Valid ClientPhoneDto clientPhoneDto
    ) {
        clientService.addAdditionalPhone(id, clientPhoneDto.getPhoneNumberAdditional());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Add an additional email", description = "Here you can add an additional email of client",
            tags = { "client" })
    @PutMapping("/client/{id}/second_email")
    public ResponseEntity<HttpStatus> addAdditionalEmail(@PathVariable int id,
                                                         @RequestBody @Valid ClientEmailDto clientEmailDto
    ) {
        clientService.addAdditionalEmail(id, clientEmailDto.getEmailAdditional());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Delete an additional phone number", description = "Here you can delete an additional number of client",
            tags = { "client" })
    @PutMapping("/client/{id}/cleared_phone")
    public ResponseEntity<HttpStatus> deleteAdditionalPhone(@PathVariable int id) {
        clientService.deleteAdditionalPhone(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Delete an additional email", description = "Here you can delete an additional email of client",
            tags = { "client" })
    @PutMapping("/client/{id}/cleared_email")
    public ResponseEntity<HttpStatus> deleteAdditionalEmail(@PathVariable int id) {
        clientService.deleteAdditionalEmail(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Get a client by email", description = "Here you can find a client by email",
            tags = { "client" })
    @GetMapping("/clients/email")
    public ClientDto getClientsByEmail(@RequestParam String email) {
        return convertToClientDTO(clientService.findClientByEmail(email));
    }

    @Operation(summary = "Get clients by date of birth", description = "Here you can find clients by date of birth",
            tags = { "client" })
    @GetMapping("/clients/birthdate/{pageNo}/{recordCount}")
    public List<ClientDto> getClientsByBirthdate(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate birthdate,
                                                 @PathVariable int pageNo,
                                                 @PathVariable int recordCount) {
        return clientService.findClientByBirthdate(birthdate, pageNo, recordCount)
                .stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a client by phone number", description = "Here you can find a client by phone number",
            tags = { "client" })
    @GetMapping("/clients/phone")
    public ClientDto getClientsByPhone(@RequestParam String phoneNumber) {
        return convertToClientDTO(clientService.findClientByPhone(phoneNumber));
    }

    @Operation(summary = "Get clients by name, surname and patronymic", description = "Here you can find clients by name, surname and patronymic",
            tags = { "client" })
    @GetMapping("/clients/person/{pageNo}/{recordCount}")
    public List<ClientDto> getClientsByFIO(@RequestParam String name,
                                           @RequestParam String surname,
                                           @RequestParam String patronymic,
                                           @PathVariable int pageNo,
                                           @PathVariable int recordCount) {
        return clientService.findClientByFIO(name, surname, patronymic, pageNo, recordCount)
                .stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Transfer money to another client", tags = { "client" })
    @PostMapping("/transfer")
    public String transferMoney(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam double amount) {
        clientService.transferMoney(fromAccountId, toAccountId, amount);
        return "Transfer successful";
    }

    private Client convertToClient(ClientDto clientDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(clientDTO, Client.class);
    }

    private ClientDto convertToClientDTO(Client client) {
        return modelMapper.map(client, ClientDto.class);
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

    private void ErrorClientCreatingExceptionThrow(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
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
    }
}
