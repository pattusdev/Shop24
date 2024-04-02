package com.Bk24Shop.Shop.service;

import com.Bk24Shop.Shop.entity.Drink;

import java.util.HashMap;
import java.util.List;

public interface DrinkService {
    HashMap<String, Object> createDrink(Drink drink);
    HashMap<String, Object> getAllDrinks();
    HashMap<String, Object> getDrinkById(Long id);
    HashMap<String, Object> deleteDrink(Long drinkId);
    List getMostConsumedDrinks();
}

