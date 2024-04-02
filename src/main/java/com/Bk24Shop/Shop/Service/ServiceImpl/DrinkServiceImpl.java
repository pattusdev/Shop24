package com.Bk24Shop.Shop.Service.ServiceImpl;

import com.Bk24Shop.Shop.Repository.DrinkRepository;
import com.Bk24Shop.Shop.Service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;

public class DrinkServiceImpl implements DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

}
