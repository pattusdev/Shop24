package com.Bk24Shop.Shop.service.serviceImpl;

import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.enums.Successes;
import com.Bk24Shop.Shop.entity.*;
import com.Bk24Shop.Shop.repository.OrderRepository;
import com.Bk24Shop.Shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final Map<Long, Order> orderCache = new HashMap<>();

    @Override
    public HashMap<String, Object> createOrder(Order order) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Order createdOrder = orderRepository.save(order);
            map.put("Object", createdOrder);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorCode(Errors.error7.code);
            error.setErrorMessage(Errors.error7.message);
            map.put("Object", error);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getAllOrders() {
        HashMap<String, Object> map = new HashMap<>();
        List<Order> allOrders = orderRepository.findAll();
        if (allOrders.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error7.code);
            error.setErrorMessage(Errors.error7.message);
            map.put("Object", error);
        } else {
            map.put("Object", allOrders);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getOrderById(Long orderId) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Order> findOrder = orderRepository.findById(orderId);
        if (findOrder.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error7.code);
            error.setErrorMessage(Errors.error7.message);
            map.put("Object", error);
        } else {
            map.put("Object", findOrder.get());
        }
        return map;
    }

    @Override
    public HashMap<String, Object> deleteOrder(Long orderId) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Order> findOrder = orderRepository.findById(orderId);
        if (findOrder.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error7.code);
            error.setErrorMessage(Errors.error7.message);
            map.put("Object", error);
        } else {
            orderRepository.deleteById(orderId);
            Success success = new Success();
            success.setSuccessCode(Successes.success1.code);
            success.setSuccessMessage(Successes.success1.message);
            map.put("success", success);
        }
        return map;
    }


    @Override
    public HashMap<String, Object> findTopFiveOrdersByDifferentClients() {
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<Object[]> topFiveOrders = orderRepository.findTopFiveOrdersByDifferentClients();
            map.put("orders", topFiveOrders);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorCode(Errors.error7.code);
            error.setErrorMessage(Errors.error7.message);
            map.put("error", error);
        }
        return map;

    }

    @Override
    public HashMap<String, Object> findTopTenPaidOrdersWithDetails() {
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<Object[]> topTenPaidOrders = orderRepository.findTopTenPaidOrdersWithDetails();
            map.put("orders", topTenPaidOrders);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorCode(Errors.error7.code);
            error.setErrorMessage(Errors.error7.message);
            map.put("error", error);
        }
        return map;
    }
}
