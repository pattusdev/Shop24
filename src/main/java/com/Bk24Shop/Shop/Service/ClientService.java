package com.Bk24Shop.Shop.service;


import com.Bk24Shop.Shop.entity.Client;

import java.util.HashMap;

public interface ClientService {
    HashMap<String, Object> createClient(Client client);
    HashMap<String, Object> getAllClients();
    HashMap<String, Object> getClientById(Long clientId);
    HashMap<String, Object> deleteClient(Long clientId);
}
