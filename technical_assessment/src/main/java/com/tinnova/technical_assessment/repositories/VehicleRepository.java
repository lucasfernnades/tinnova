package com.tinnova.technical_assessment.repositories;

import com.tinnova.technical_assessment.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT * FROM vehicle WHERE make = :make AND release_year = :releaseYear AND color = :color", nativeQuery = true)
    List<Vehicle> getAllVehicleWithParameters(String make, Integer releaseYear, String color);

    @Query(value = "SELECT COUNT(*) FROM vehicle WHERE sold = false", nativeQuery = true)
    Long getCountUnsoldVehicles();

    @Query(value = "SELECT COUNT(*) FROM vehicle WHERE release_year >= :startYear AND release_year < :endYear", nativeQuery = true)
    Long getCountVehiclesByDecade(Integer startYear, Integer endYear);

    @Query(value = "SELECT COUNT(*) FROM vehicle WHERE make = :make", nativeQuery = true)
    Long getCountByMake(String make);

    @Query(value = "SELECT * FROM vehicle WHERE created >= DATEADD('DAY', -7, CURRENT_DATE())", nativeQuery = true)
    List<Vehicle> getAllRegisteredVehiclesAtLastWeek();
}
