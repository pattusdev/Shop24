package com.Bk24Shop.Shop.repository;

import com.Bk24Shop.Shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
        @Query(value = "SELECT DISTINCT o.id, o.client_id, o.timestamp " +
                "FROM shop24.order o " +
                "WHERE o.id IN ( " +
                "    SELECT id " +
                "    FROM ( " +
                "        SELECT id, ROW_NUMBER() OVER (PARTITION BY client_id ORDER BY timestamp DESC) AS row_num " +
                "        FROM shop24.order " +
                "    ) AS temp " +
                "    WHERE row_num = 1 " +
                ") " +
                "ORDER BY o.timestamp DESC " +
                "LIMIT 5", nativeQuery = true)
        List<Object[]> findTopFiveOrdersByDifferentClients();

        @Query("SELECT o.id, c.name AS clientName " +
                "FROM Order o " +
                "JOIN o.client c " +
                "WHERE o.status = 'Completed' " +
                "ORDER BY o.timestamp DESC " +
                "LIMIT 10")
        List<Object[]> findTopTenPaidOrdersWithDetails();





}
