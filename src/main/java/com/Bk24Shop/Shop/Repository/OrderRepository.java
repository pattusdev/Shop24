package com.Bk24Shop.Shop.repository;

import com.Bk24Shop.Shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
        @Query(value = "SELECT DISTINCT o.order_id, o.client_id, o.order_date " +
                "FROM bk.order o " +
                "WHERE o.order_id IN ( " +
                "    SELECT order_id " +
                "    FROM ( " +
                "        SELECT order_id, ROW_NUMBER() OVER (PARTITION BY client_id ORDER BY order_date DESC) AS row_num " +
                "        FROM bk.order " +
                "    ) AS temp " +
                "    WHERE row_num = 1 " +
                ") " +
                "ORDER BY o.order_date DESC " +
                "LIMIT 5", nativeQuery = true)
        List<Object[]> findTopFiveOrdersByDifferentClients();

        @Query("SELECT o.orderId, c.name AS clientName " +
                "FROM Order o " +
                "JOIN o.client c " +
                "WHERE o.status = 'Completed' " +
                "ORDER BY o.orderDate DESC " +
                "LIMIT 10")
        List<Object[]> findTopTenPaidOrdersWithDetails();





}
