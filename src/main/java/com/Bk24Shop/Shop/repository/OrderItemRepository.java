package com.Bk24Shop.Shop.repository;

import com.Bk24Shop.Shop.entity.OrderItem;
import com.Bk24Shop.Shop.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

}
