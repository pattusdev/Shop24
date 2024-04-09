package com.Bk24Shop.Shop.repository;

import com.Bk24Shop.Shop.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query(value = "SELECT *, " +
            "   ( 3959 * acos( cos( radians(:latitude) ) " +
            "   * cos( radians( c.latitude ) ) " +
            "   * cos( radians( c.longitude ) - radians(:longitude) ) " +
            "   + sin( radians(:latitude) ) " +
            "   * sin( radians( c.latitude ) ) ) ) AS distance " +
            "FROM Cargo c " +
            "ORDER BY distance " +
            "LIMIT 3", nativeQuery = true)
    List<Cargo> findClosestCargoCompanies(@Param("latitude") double latitude, @Param("longitude") double longitude);
}
