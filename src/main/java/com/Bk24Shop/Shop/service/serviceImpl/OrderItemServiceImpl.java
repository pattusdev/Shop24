package com.Bk24Shop.Shop.service.serviceImpl;

import com.Bk24Shop.Shop.enums.Errors;
import com.Bk24Shop.Shop.entity.Error;
import com.Bk24Shop.Shop.enums.Successes;
import com.Bk24Shop.Shop.entity.OrderItem;
import com.Bk24Shop.Shop.entity.OrderItemId;
import com.Bk24Shop.Shop.entity.Success;
import com.Bk24Shop.Shop.repository.OrderItemRepository;
import com.Bk24Shop.Shop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    private final Map<OrderItemId, OrderItem> orderItemCache = new HashMap<>();

    @Override
    public HashMap<String, Object> createOrderItem(OrderItem orderItem) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            map.put("Object", createdOrderItem);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorCode(Errors.error6.code);
            error.setErrorMessage(Errors.error6.message);
            map.put("Object", error);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getAllOrderItems() {
        HashMap<String, Object> map = new HashMap<>();
        List<OrderItem> allOrderItems = orderItemRepository.findAll();
        if (allOrderItems.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error6.code);
            error.setErrorMessage(Errors.error6.message);
            map.put("Object", error);
        } else {
            map.put("Object", allOrderItems);
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getOrderItemById(Long orderId, Long drinkId) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<OrderItem> findOrderItem = orderItemRepository.findById(new OrderItemId(orderId, drinkId));
        if (findOrderItem.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error6.code);
            error.setErrorMessage(Errors.error6.message);
            map.put("Object", error);
        } else {
            map.put("Object", findOrderItem.get());
        }
        return map;
    }

    @Override
    public HashMap<String, Object> deleteOrderItem(Long orderId, Long drinkId) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<OrderItem> findOrderItem = orderItemRepository.findById(new OrderItemId(orderId, drinkId));
        if (findOrderItem.isEmpty()) {
            Error error = new Error();
            error.setErrorCode(Errors.error6.code);
            error.setErrorMessage(Errors.error6.message);
            map.put("Object", error);
        } else {
            orderItemRepository.deleteById(new OrderItemId(orderId, drinkId));
            Success success = new Success();
            success.setSuccessCode(Successes.success1.code);
            success.setSuccessMessage(Successes.success1.message);
            map.put("success", success);
        }
        return map;
    }
}
