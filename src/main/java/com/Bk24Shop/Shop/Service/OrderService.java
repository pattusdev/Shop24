package com.Bk24Shop.Shop.service;


import com.Bk24Shop.Shop.dto.OrderDTO;
import com.Bk24Shop.Shop.entity.Order;

import java.util.HashMap;

public interface OrderService {
    HashMap<String, Object> createOrder(OrderDTO order);
    HashMap<String, Object> getAllOrders();
    HashMap<String, Object> getOrderById(Long id);
    HashMap<String, Object> deleteOrder(Long id);
    HashMap<String, Object> findTopFiveOrdersByDifferentClients();

    HashMap<String, Object> findTopTenPaidOrdersWithDetails();

}
