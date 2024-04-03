package com.Bk24Shop.Shop.service.serviceImpl;

import com.Bk24Shop.Shop.dto.MostConsumedDrinkDTO;
import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.enums.Successes;
import com.Bk24Shop.Shop.entity.Drink;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.repository.DrinkRepository;
import com.Bk24Shop.Shop.service.DrinkService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DrinkServiceImpl implements DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    private final Map<Long, Drink> drinkCache = new HashMap<>();

    @PostConstruct
    public void initCache() {
        List<Drink> drinks = drinkRepository.findAll();
        drinks.forEach(drink -> drinkCache.put(drink.getId(), drink));
    }


    @Override
    public HashMap createDrink(Drink drink){

        HashMap<String, Object> map = new HashMap<>();
        try{
            Drink createdDrink = drinkRepository.save(drink);
            map.put("Object",createdDrink);
        } catch (Exception e){
            Error error = new Error();
            error.setErrorCode(Errors.error1.code);
            error.setErrorMessage(Errors.error1.message);
            map.put("Object",error);
        }
        return map;

    }

    @Override
    public HashMap<String, Object> getAllDrinks() {
        HashMap<String, Object> map = new HashMap<>();
        if (drinkRepository.findAll().isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error1.code);
            error.setErrorMessage(Errors.error1.message);
            map.put("Object", error);
        } else {
            List<Drink> listPersons = drinkRepository.findAll();
            map.put("Object", listPersons);
        }
        return map;
    }


    @Override
    public HashMap<String, Object> getDrinkById(Long id) {
        HashMap<String, Object> map = new HashMap<>();

        Optional<Drink> findDrink = drinkRepository.findById(id);
        if (findDrink.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error1.code);
            error.setErrorMessage(Errors.error1.message);
            map.put("Object", error);
        } else {
            map.put("Object", findDrink.get());
        }

        return map;
    }


    @Override
    public HashMap<String, Object> deleteDrink(Long id) {
        HashMap<String, Object> map = new HashMap<>();

        Optional<Drink> findDrink = drinkRepository.findById(id);
        if (findDrink.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error1.code);
            error.setErrorMessage(Errors.error1.message);
            map.put("Object", error);
        } else {
            drinkRepository.deleteById(id);
            Success success = new Success();
            success.setSuccessCode(Successes.success1.code);
            success.setSuccessMessage(Successes.success1.message);
            map.put("success", success); // Use a different key for the success object
        }

        return map;
    }

    @Override
    public List<MostConsumedDrinkDTO> getMostConsumedDrinks() {
        return drinkRepository.findMostConsumedDrinks();
    }
}

