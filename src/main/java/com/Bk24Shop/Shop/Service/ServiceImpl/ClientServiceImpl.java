package com.Bk24Shop.Shop.Service.ServiceImpl;

import com.Bk24Shop.Shop.Repository.ClientRepository;
import com.Bk24Shop.Shop.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
}
