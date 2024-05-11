package com.Bk24Shop.Shop.controller;

import com.Bk24Shop.Shop.dto.OrderDTO; // Import OrderDTO from dto
import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.entity.*;
import com.Bk24Shop.Shop.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@Tag(name = "Orders", description = "Orders management APIs")
@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody OrderDTO order) {
        HashMap<String, Object> map = orderService.createOrder(order);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(map.get("Object"));
        }
    }

    @GetMapping
    public ResponseEntity getAllOrders() {
        HashMap<String, Object> map = orderService.getAllOrders();
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }

    @GetMapping("delete/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId) {
        HashMap<String, Object> map = orderService.deleteOrder(orderId);
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

    @GetMapping("/{orderId}")
    public ResponseEntity getOrderById(@PathVariable Long orderId) {
        HashMap<String, Object> map = orderService.getOrderById(orderId);
        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            return ResponseEntity.ok(map.get("Object"));
        }
    }


    @GetMapping("/topFiveOrdersByDifferentClients")
    public ResponseEntity getTopFiveOrdersByDifferentClients() {
        HashMap<String, Object> map = orderService.findTopFiveOrdersByDifferentClients();

        if (map.containsKey("error")) {
            Error error = (Error) map.get("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            List<Order> orders = (List<Order>) map.get("orders");
            return ResponseEntity.ok(orders);

        }
    }
    @GetMapping("/topTenPaidOrders")
    public ResponseEntity<HashMap<String, Object>> findTopTenPaidOrdersWithDetails() {
        HashMap<String, Object> response = orderService.findTopTenPaidOrdersWithDetails();
        return ResponseEntity.ok(response);
    }


}
