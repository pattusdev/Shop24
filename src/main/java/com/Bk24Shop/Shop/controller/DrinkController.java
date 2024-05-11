package com.Bk24Shop.Shop.controller;

import com.Bk24Shop.Shop.dto.DrinkDTO; // Import DrinkDTO from dto
import com.Bk24Shop.Shop.dto.MostConsumedDrinkDTO;
import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.entity.Drink;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.service.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.List;

@Tag(name = "Drink", description = "Drinks management APIs")
@RestController
@RequestMapping("api/drinks")
@EnableWebMvc
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @Operation(
            summary = "Create a Drink",
            description = "Create Drink  and its Specification Details.",
            tags = { "drinks", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Drink.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/create")
    public ResponseEntity createDrink(@RequestBody DrinkDTO drink) {
        HashMap<String, Object> map = new HashMap<>();
        map = drinkService.createDrink(drink);
        return new ResponseEntity(map.get("Object"), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve List of All Drinks",
            description = "Get a List All Drinks Details. The response is List of All Drinks.",
            tags = { "drinks", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Drink.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity getAllDrinks() {
        HashMap<String, Object> map = new HashMap<>();
        map = drinkService.getAllDrinks();
        return new ResponseEntity<>(map.get("Object"), HttpStatus.OK);
    }
    @Operation(
            summary = "Delete a Drink by ID",
            description = "Delete a Drink by specifying its id. The response is Drink Deleted with its id.",
            tags = { "drinks", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Drink.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("delete/{drinkId}")
    public ResponseEntity deleteDrink(@PathVariable Long drinkId) {
        HashMap<String, Object> map = drinkService.deleteDrink(drinkId);

        if (map.containsKey("success")) {
            Success success = (Success) map.get("success");
            return ResponseEntity.ok(success);
        } else if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            // Return custom error message
            Error error = new Error();
            error.setErrorCode(Errors.error1.code);
            error.setErrorMessage(Errors.error1.message);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @Operation(
            summary = "Retrieve a Drink by ID",
            description = "Get a Drink Details by specifying its id. The response is Drink Details with id, Name, Title and Type.",
            tags = { "drinks", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Drink.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @GetMapping("/{drinkId}")
    public ResponseEntity getDrinkById(@PathVariable Long drinkId) {
        HashMap<String, Object> map = drinkService.getDrinkById(drinkId);
        if (map.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map.get("error"));
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }
    @Operation(
            summary = "Retrieve Most Consumed Drink",
            description = "Get Most Consumed Drink Details. The response is Get Most Consumed Drinks.",
            tags = { "drinks", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Drink.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/mostConsumed")
    public ResponseEntity getMostConsumedDrinks() {
        List<MostConsumedDrinkDTO> mostConsumedDrinks = drinkService.getMostConsumedDrinks();

        if (mostConsumedDrinks == null || mostConsumedDrinks.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error1.code);
            error.setErrorMessage(Errors.error1.message);
            return ResponseEntity.status(HttpStatus.OK).body(error);
        } else {
            return ResponseEntity.ok(mostConsumedDrinks);
        }
    }

}
