package com.Bk24Shop.Shop.service;


import com.Bk24Shop.Shop.entity.Cargo;

import java.util.HashMap;

public interface CargoService {

    HashMap<String, Object> createCargo(Cargo cargo);
    HashMap<String, Object> getAllCargo();
    HashMap<String, Object> getCargoById(Long cargoId);
    HashMap<String, Object> deleteCargo(Long cargoId);
}
