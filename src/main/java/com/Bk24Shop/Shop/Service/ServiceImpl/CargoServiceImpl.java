package com.Bk24Shop.Shop.service.serviceImpl;

import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.enums.Successes;
import com.Bk24Shop.Shop.entity.Cargo;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.repository.CargoRepository;
import com.Bk24Shop.Shop.service.CargoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    private final Map<Long, Cargo> cargoCache = new HashMap<>();

    @PostConstruct
    public void initCache() {
        List<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> cargoCache.put(cargo.getCargoId(), cargo));
    }

    @Override
    public HashMap<String, Object> createCargo(Cargo cargo) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Cargo createdCargo = cargoRepository.save(cargo);
            map.put("Object", createdCargo);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorCode(Errors.error2.code);
            error.setErrorMessage(Errors.error2.message);
            map.put("Object", error);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getAllCargo() {
        HashMap<String, Object> map = new HashMap<>();
        List<Cargo> allCargo = cargoRepository.findAll();
        if (allCargo.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error2.code);
            error.setErrorMessage(Errors.error2.message);
            map.put("Object", error);
        } else {
            map.put("Object", allCargo);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getCargoById(Long cargoId) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Cargo> findCargo = cargoRepository.findById(cargoId);
        if (findCargo.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error2.code);
            error.setErrorMessage(Errors.error2.message);
            map.put("Object", error);
        } else {
            map.put("Object", findCargo.get());
        }
        return map;
    }

    @Override
    public HashMap<String, Object> deleteCargo(Long cargoId) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Cargo> findCargo = cargoRepository.findById(cargoId);
        if (findCargo.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error2.code);
            error.setErrorMessage(Errors.error2.message);
            map.put("Object", error);
        } else {
            cargoRepository.deleteById(cargoId);
            Success success = new Success();
            success.setSuccessCode(Successes.success1.code);
            success.setSuccessMessage(Successes.success1.message);
            map.put("success", success); // Use a different key for the success object
        }
        return map;
    }
}
