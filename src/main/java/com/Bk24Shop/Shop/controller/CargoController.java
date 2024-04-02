package com.Bk24Shop.Shop.controller;

import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.entity.Cargo;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.service.CargoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@Tag(name = "Cargo", description = "Cargos management APIs")
@RestController
@Slf4j
@RequestMapping("api/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @PostMapping("/create")
    public ResponseEntity createCargo(@RequestBody Cargo cargo) {
        HashMap<String, Object> map = cargoService.createCargo(cargo);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            log.info("Cargo - " + cargo.getName() + " created Successfully!!");
            return ResponseEntity.status(HttpStatus.CREATED).body(map.get("Object"));
        }
    }

    @GetMapping
    public ResponseEntity getAllCargos() {
        HashMap<String, Object> map = cargoService.getAllCargo();
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }

    @GetMapping("delete/{cargoId}")
    public ResponseEntity deleteCargo(@PathVariable Long cargoId) {
        HashMap<String, Object> map = cargoService.deleteCargo(cargoId);
        if (map.containsKey("success")) {
            Success success = (Success) map.get("success");
            return ResponseEntity.ok(success);
        } else if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            Error error = new Error();
            error.setErrorCode(Errors.error1.code);
            error.setErrorMessage(Errors.error1.message);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity getCargoById(@PathVariable Long cargoId) {
        HashMap<String, Object> map = cargoService.getCargoById(cargoId);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }
}
