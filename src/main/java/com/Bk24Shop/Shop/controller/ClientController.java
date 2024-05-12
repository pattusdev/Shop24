package com.Bk24Shop.Shop.controller;

import com.Bk24Shop.Shop.dto.ClientDTO; // Import ClientDTO from dto
import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.entity.Client;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@Tag(name = "Clients", description = "Clients management APIs")
@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity createClient(@RequestBody ClientDTO client) {
        HashMap<String, Object> map = clientService.createClient(client);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(map.get("Object"));
        }
    }

    @GetMapping
    public ResponseEntity getAllClients() {
        HashMap<String, Object> map = clientService.getAllClients();
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }

    @GetMapping("delete/{clientId}")
    public ResponseEntity deleteClient(@PathVariable Long clientId) {
        HashMap<String, Object> map = clientService.deleteClient(clientId);
        if (map.containsKey("success")) {
            Success success = (Success) map.get("success");
            return ResponseEntity.ok(success);
        } else if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            Error error = new Error();
            error.setErrorCode(Errors.error1.code);
            error.setErrorMessage(Errors.error1.message);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{clientId}")
    public ResponseEntity getClientById(@PathVariable Long clientId) {
        HashMap<String, Object> map = clientService.getClientById(clientId);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }
}
