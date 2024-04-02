package com.Bk24Shop.Shop.Repository;

import com.Bk24Shop.Shop.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
