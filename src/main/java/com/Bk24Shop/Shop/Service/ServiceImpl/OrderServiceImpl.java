package com.Bk24Shop.Shop.Service.ServiceImpl;

import com.Bk24Shop.Shop.Repository.OrderRepository;
import com.Bk24Shop.Shop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
}
