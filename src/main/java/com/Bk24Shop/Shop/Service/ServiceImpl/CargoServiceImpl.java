package com.Bk24Shop.Shop.service.serviceImpl;

import com.Bk24Shop.Shop.dto.CargoDTO;
import com.Bk24Shop.Shop.entity.*;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.enums.Successes;
import com.Bk24Shop.Shop.repository.CargoRepository;
import com.Bk24Shop.Shop.repository.ClientRepository;
import com.Bk24Shop.Shop.repository.DrinkRepository;
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
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DrinkRepository drinkRepository;

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

//    @Override
//    public HashMap<String, Object> findNearestCargoCompany(double latitude, double longitude) {
//        List<Cargo> allCargoCompanies = cargoRepository.findAll();
//
//        Cargo nearestCargo = null;
//        double minDistance = Double.MAX_VALUE;
//
//        for (Cargo cargo : allCargoCompanies) {
//            double distance = calculateDistance(latitude, longitude, cargo.getLatitude(), cargo.getLongitude());
//            if (distance < minDistance) {
//                minDistance = distance;
//                nearestCargo = cargo;
//            }
//        }
//
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("nearestCargoCompany", nearestCargo);
//        result.put("distance", minDistance);
//
//        return result;
//    }
//
//    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
//        try {
//            DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(geoApiContext)
//                    .origins(new com.google.maps.model.LatLng(lat1, lon1))
//                    .destinations(new com.google.maps.model.LatLng(lat2, lon2))
//                    .await();
//            DistanceMatrixRow[] rows = distanceMatrix.rows;
//            if (rows != null && rows.length > 0) {
//                DistanceMatrixElement[] elements = rows[0].elements;
//                if (elements != null && elements.length > 0) {
//                    if (elements[0].status.equals("OK")) {
//                        return elements[0].distance.inMeters / 1000.0; // Convert to kilometers
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Double.MAX_VALUE; // Return a very large value if distance calculation fails
//    }
    @Override
    public HashMap<String, Object> findNearestCargoAndAvailableDrinks(Long clientId) {
        // Retrieve client's location
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isEmpty()) {
            // Handle client not found
            return new HashMap<>(); // Return an empty map or handle error as needed
        }
        Client client = optionalClient.get();

        // Retrieve nearest cargo company
        List<Cargo> nearestCargoCompanies = cargoRepository.findClosestCargoCompanies(client.getLatitude(), client.getLongitude());
        // Filter cargo companies within 3km
        nearestCargoCompanies = nearestCargoCompanies.stream()
                .filter(cargo -> calculateDistance(client.getLatitude(), client.getLongitude(), cargo.getLatitude(), cargo.getLongitude()) <= 3.0)
                .collect(Collectors.toList());

        // Retrieve all available drinks
        HashMap<String, Object> allDrinksResult = getAllDrinks();
        if (allDrinksResult.containsKey("error")) {
            // Handle error when fetching drinks
            return allDrinksResult;
        }
        List<Drink> availableDrinks = (List<Drink>) allDrinksResult.get("Object");

        HashMap<String, Object> result = new HashMap<>();
        result.put("nearestCargoCompanies", nearestCargoCompanies);
        result.put("availableDrinks", availableDrinks);
        return result;
    }

        private HashMap<String, Object> getAllDrinks() {
            HashMap<String, Object> map = new HashMap<>();
            List<Drink> drinks = drinkRepository.findAll();
            if (drinks.isEmpty()) {
                Error error = new Error();
                error.setErrorCode(Errors.error1.code);
                error.setErrorMessage(Errors.error1.message);
                map.put("error", error);
            } else {
                map.put("Object", drinks);
            }
            return map;
        }

        private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            return dist;
        }

    //    @Override
//    public HashMap<String, Object> findClosestCargoCompanies(double latitude, double longitude) {
//        HashMap<String, Object> result = new HashMap<>();
//        try {
//            List<Cargo> closestCargoCompanies = cargoRepository.findClosestCargoCompanies(latitude, longitude);
//            result.put("closestCargoCompanies", closestCargoCompanies);
//            return result;
//        } catch (Exception e) {
//            Error error = new Error();
//            error.setErrorCode(Errors.error4.code);
//            error.setErrorMessage(Errors.error4.message);
//            result.put("error", error);
//            return result;
//        }
//    }

    @Override
    public HashMap<String, Object> findClosestCargoCompanies(Long clientId) {
        HashMap<String, Object> result = new HashMap<>();

        // Retrieve client's location
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            // Handle client not found
            result.put("error", "Client not found");
            return result;
        }

        // Retrieve closest cargo companies using the repository method
        List<Cargo> closestCargoCompanies = cargoRepository.findClosestCargoCompanies(client.getLatitude(), client.getLongitude());

        // Add the result to the HashMap
        result.put("closestCargoCompanies", closestCargoCompanies);
        return result;
    }

}
