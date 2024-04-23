package com.Bk24Shop.Shop.service;

import com.Bk24Shop.Shop.dto.DrinkDTO;

import java.util.HashMap;
import java.util.List;

public interface DrinkService {
    HashMap<String, Object> createDrink(DrinkDTO drink);
    HashMap<String, Object> getAllDrinks();
    HashMap<String, Object> getDrinkById(Long id);
    HashMap<String, Object> deleteDrink(Long id);
    List getMostConsumedDrinks();
}

