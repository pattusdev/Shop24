package com.Bk24Shop.Shop.service.serviceImpl;

import com.Bk24Shop.Shop.dto.CargoDTO;
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

import com.google.maps.GeoApiContext;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.DistanceMatrixElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    public CargoServiceImpl(CargoRepository cargoRepository, GeoApiContext geoApiContext) {
        this.cargoRepository = cargoRepository;
        this.geoApiContext = geoApiContext;
    }
    private final GeoApiContext geoApiContext;

    private final Map<Long, Cargo> cargoCache = new HashMap<>();

    @PostConstruct
    public void initCache() {
        List<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> cargoCache.put(cargo.getId(), cargo));
    }

    @Override
    public HashMap<String, Object> createCargo(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();
        cargo.setType(cargoDTO.getType());
        cargo.setName(cargoDTO.getName());
        cargo.setAddress(cargoDTO.getAddress());
        cargo.setLongitude(cargoDTO.getLongitude());
        cargo.setLatitude(cargoDTO.getLatitude());
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
    public HashMap<String, Object> getCargoById(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Cargo> findCargo = cargoRepository.findById(id);
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
    public HashMap<String, Object> deleteCargo(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Cargo> findCargo = cargoRepository.findById(id);
        if (findCargo.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error2.code);
            error.setErrorMessage(Errors.error2.message);
            map.put("Object", error);
        } else {
            cargoRepository.deleteById(id);
            Success success = new Success();
            success.setSuccessCode(Successes.success1.code);
            success.setSuccessMessage(Successes.success1.message);
            map.put("success", success); // Use a different key for the success object
        }
        return map;
    }

    @Override
    public HashMap<String, Object> findNearestCargoCompany(double latitude, double longitude) {
        List<Cargo> allCargoCompanies = cargoRepository.findAll();

        Cargo nearestCargo = null;
        double minDistance = Double.MAX_VALUE;

        for (Cargo cargo : allCargoCompanies) {
            double distance = calculateDistance(latitude, longitude, cargo.getLatitude(), cargo.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearestCargo = cargo;
            }
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("nearestCargoCompany", nearestCargo);
        result.put("distance", minDistance);

        return result;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        try {
            DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(geoApiContext)
                    .origins(new com.google.maps.model.LatLng(lat1, lon1))
                    .destinations(new com.google.maps.model.LatLng(lat2, lon2))
                    .await();
            DistanceMatrixRow[] rows = distanceMatrix.rows;
            if (rows != null && rows.length > 0) {
                DistanceMatrixElement[] elements = rows[0].elements;
                if (elements != null && elements.length > 0) {
                    if (elements[0].status.equals("OK")) {
                        return elements[0].distance.inMeters / 1000.0; // Convert to kilometers
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.MAX_VALUE; // Return a very large value if distance calculation fails
    }

}
