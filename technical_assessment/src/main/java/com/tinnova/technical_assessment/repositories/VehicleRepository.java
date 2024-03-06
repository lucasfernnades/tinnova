package com.tinnova.technical_assessment.repositories;

import com.tinnova.technical_assessment.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT * FROM vehicle WHERE make = :make AND releaseYear = :releaseYear AND color = :color", nativeQuery = true)
    List<Vehicle> getAllVehicleWithParameters(String make, int releaseYear, String color);
}
