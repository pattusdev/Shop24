package com.Bk24Shop.Shop.service.serviceImpl;

import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.enums.Successes;
import com.Bk24Shop.Shop.entity.Client;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.repository.ClientRepository;
import com.Bk24Shop.Shop.service.ClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private final Map<Long, Client> clientCache = new HashMap<>();

    @PostConstruct
    public void initCache() {
        List<Client> clients = clientRepository.findAll();
        clients.forEach(client -> clientCache.put(client.getId(), client));
    }

    @Override
    public HashMap<String, Object> createClient(Client client) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Client createdClient = clientRepository.save(client);
            clientCache.put(createdClient.getId(), createdClient);
            map.put("Object", createdClient);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorCode(Errors.error4.code);
            error.setErrorMessage(Errors.error4.message);
            map.put("Object", error);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getAllClients() {
        HashMap<String, Object> map = new HashMap<>();
        List<Client> allClients = clientRepository.findAll();
        if (allClients.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error4.code);
            error.setErrorMessage(Errors.error4.message);
            map.put("Object", error);
        } else {
            map.put("Object", allClients);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getClientById(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Client> findClient = clientRepository.findById(id);
        if (findClient.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error4.code);
            error.setErrorMessage(Errors.error4.message);
            map.put("Object", error);
        } else {
            map.put("Object", findClient.get());
        }
        return map;
    }

    @Override
    public HashMap<String, Object> deleteClient(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Client> findClient = clientRepository.findById(id);
        if (findClient.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error4.code);
            error.setErrorMessage(Errors.error4.message);
            map.put("Object", error);
        } else {
            clientRepository.deleteById(id);
            clientCache.remove(id);
            Success success = new Success();
            success.setSuccessCode(Successes.success1.code);
            success.setSuccessMessage(Successes.success1.message);
            map.put("success", success);
        }
        return map;
    }
}
