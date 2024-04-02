package com.Bk24Shop.Shop.controller;

import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.entity.OrderItem;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.service.OrderItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@Tag(name = "Order Items", description = "Order Items management APIs")
@RestController
@RequestMapping("api/orderItems")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity createOrderItem(@RequestBody OrderItem orderItem) {
        HashMap<String, Object> map = orderItemService.createOrderItem(orderItem);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(map.get("Object"));
        }
    }

    @GetMapping
    public ResponseEntity getAllOrderItems() {
        HashMap<String, Object> map = orderItemService.getAllOrderItems();
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }

    @GetMapping("delete/{orderId}/{drinkId}")
    public ResponseEntity deleteOrderItem(@PathVariable Long orderId, @PathVariable Long drinkId) {
        HashMap<String, Object> map = orderItemService.deleteOrderItem(orderId, drinkId);
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

    @GetMapping("/{orderId}/{drinkId}")
    public ResponseEntity getOrderItemById(@PathVariable Long orderId, @PathVariable Long drinkId) {
        HashMap<String, Object> map = orderItemService.getOrderItemById(orderId, drinkId);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }
}
