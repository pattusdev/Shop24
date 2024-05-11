package com.Bk24Shop.Shop.controller;

import com.Bk24Shop.Shop.dto.CargoDTO; // Import cargoDTO from dto
import com.Bk24Shop.Shop.entity.Drink;
import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.entity.Cargo;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Tag(name = "Cargo", description = "Cargos management APIs")
@RestController
@Slf4j
@RequestMapping("api/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Operation(
            summary = "Create a Cargo",
            description = "Create Cargo  and its Specification Details.",
            tags = { "cargos", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Cargo.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/create")
    public ResponseEntity createCargo(@RequestBody CargoDTO cargo) {
        HashMap<String, Object> map = cargoService.createCargo(cargo);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            log.info("Cargo - " + cargo.getName() + " created Successfully!!");
            return ResponseEntity.status(HttpStatus.CREATED).body(map.get("Object"));
        }
    }

    @Operation(
            summary = "Retrieve List of All Cargos",
            description = "Get a List All Cargos Details. The response is List of All Cargos.",
            tags = { "cargos", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Cargo.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

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

    @Operation(
            summary = "Delete a Cargo by ID",
            description = "Delete a Cargo by specifying its id. The response is Cargo Deleted with its id.",
            tags = { "cargo", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Cargo.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

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

    @Operation(
            summary = "Retrieve a Cargo by ID",
            description = "Get a Cargo Details by specifying its id. The response is Cargo Details",
            tags = { "cargo", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Cargo.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

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

    @Operation(
            summary = "Retrieve Nearest And Available Drinks",
            description = "Get Nearest And Available Drinks. The response is Get Nearest And Available Drinks.",
            tags = { "cargo", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Cargo.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @GetMapping("/{clientId}/nearest-and-available-drinks")
    public ResponseEntity<?> getNearestCargoAndAvailableDrinks(@PathVariable Long clientId) {
        HashMap<String, Object> result = cargoService.findNearestCargoAndAvailableDrinks(clientId);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Retrieve Closest Cargos to the Client",
            description = "Get Closest Cargos to the Client. The response is Get Closest Cargos.",
            tags = { "cargos", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Cargo.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @GetMapping("/{clientId}/closest-cargos")
    public ResponseEntity<?> getClosestCargoCompanies(@PathVariable Long clientId) {
        // Retrieve the 3 closest cargo companies for the given client
        HashMap<String, Object> cargoResult = cargoService.findClosestCargoCompanies(clientId);

        if (cargoResult.containsKey("error")) {
            // If there's an error, return the error response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cargoResult.get("error"));
        } else {
            // If successful, return the closest cargo companies
            List<Cargo> closestCargoCompanies = (List<Cargo>) cargoResult.get("closestCargoCompanies");
            return ResponseEntity.ok(closestCargoCompanies);
        }
    }
}
