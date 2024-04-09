package com.Bk24Shop.Shop.service;


import com.Bk24Shop.Shop.dto.CargoDTO;
import com.Bk24Shop.Shop.entity.Cargo;

import java.util.HashMap;

public interface CargoService {

    HashMap<String, Object> createCargo(CargoDTO cargo);
    HashMap<String, Object> getAllCargo();
    HashMap<String, Object> getCargoById(Long id);
    HashMap<String, Object> deleteCargo(Long id);
    HashMap<String, Object> findNearestCargoAndAvailableDrinks(Long clientId);
    HashMap<String, Object> findClosestCargoCompanies(Long clientId);
}
