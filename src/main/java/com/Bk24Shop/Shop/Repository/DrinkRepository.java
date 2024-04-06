package com.Bk24Shop.Shop.repository;

import com.Bk24Shop.Shop.dto.MostConsumedDrinkDTO;
import com.Bk24Shop.Shop.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DrinkRepository extends JpaRepository<Drink, Long> {

    @Query("SELECT new com.Bk24Shop.Shop.dto.MostConsumedDrinkDTO(d.id, d.name, SUM(oi.quantity) AS totalQuantity) " +
            "FROM Drink d " +
            "JOIN OrderItem oi ON d.id = oi.drink.id " +
            "GROUP BY d.id, d.name " +
            "ORDER BY totalQuantity DESC")
    List<MostConsumedDrinkDTO> findMostConsumedDrinks();
}
