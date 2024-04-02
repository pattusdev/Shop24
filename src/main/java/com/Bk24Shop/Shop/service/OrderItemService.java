package com.Bk24Shop.Shop.service;


import com.Bk24Shop.Shop.entity.OrderItem;

import java.util.HashMap;

public interface OrderItemService {
    HashMap<String, Object> createOrderItem(OrderItem orderItem);
    HashMap<String, Object> getAllOrderItems();
    HashMap<String, Object> getOrderItemById(Long orderId, Long drinkId);
    HashMap<String, Object> deleteOrderItem(Long orderId, Long drinkId);
}
