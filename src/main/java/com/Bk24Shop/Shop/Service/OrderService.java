package com.Bk24Shop.Shop.service;


import com.Bk24Shop.Shop.entity.Order;

import java.util.HashMap;

public interface OrderService {
    HashMap<String, Object> createOrder(Order order);
    HashMap<String, Object> getAllOrders();
    HashMap<String, Object> getOrderById(Long orderId);
    HashMap<String, Object> deleteOrder(Long orderId);
    HashMap<String, Object> findTopFiveOrdersByDifferentClients();

    HashMap<String, Object> findTopTenPaidOrdersWithDetails();

}
