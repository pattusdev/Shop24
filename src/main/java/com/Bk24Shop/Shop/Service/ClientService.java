package com.Bk24Shop.Shop.service;


import com.Bk24Shop.Shop.dto.ClientDTO;
import com.Bk24Shop.Shop.entity.Client;

import java.util.HashMap;

public interface ClientService {
    HashMap<String, Object> createClient(ClientDTO client);
    HashMap<String, Object> getAllClients();
    HashMap<String, Object> getClientById(Long id);
    HashMap<String, Object> deleteClient(Long id);
}
