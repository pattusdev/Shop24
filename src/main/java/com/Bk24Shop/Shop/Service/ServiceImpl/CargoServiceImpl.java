package com.Bk24Shop.Shop.Service.ServiceImpl;

import com.Bk24Shop.Shop.Repository.CargoRepository;
import com.Bk24Shop.Shop.Service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;

public class CargoServiceImpl implements CargoService {
    @Autowired
    private CargoRepository cargoRepository;

}
