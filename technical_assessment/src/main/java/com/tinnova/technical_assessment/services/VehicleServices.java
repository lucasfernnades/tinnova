package com.tinnova.technical_assessment.services;

import com.tinnova.technical_assessment.entities.Vehicle;
import com.tinnova.technical_assessment.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleServices {

    private final VehicleRepository repository;

    @Autowired
    public VehicleServices(VehicleRepository repository) {
        this.repository = repository;
    }

    public List<Vehicle> getAllVehicle() {
        return repository.findAll();
    }

    public List<Vehicle> getAllVehicleWithParameters(String make, int releaseYear, String color) {
        return repository.getAllVehicleWithParameters(make, releaseYear, color);
    }

    public Vehicle getVehicleById(Long id) {
        return repository.findById(id).get(); //Melhorar a logica para caso nao achar nada
    }

    public Vehicle registerVehicle(Vehicle dto) {
        Vehicle vehicle = new Vehicle(null, dto.getModel(), dto.getMake(), dto.getReleaseYear(), dto.getColor(),
                false, LocalDateTime.now(), null);

        if (!vehicle.validMakesCar(dto.getMake()))
            return null;

        return repository.save(vehicle);
    }

    public Vehicle updateAllVehicleInfo(Long id, Vehicle dto) {
        Vehicle vehicle = repository.findById(id).get();

        if (!vehicle.validMakesCar(dto.getMake()))
            return null;

        vehicle.setModel(dto.getModel());
        vehicle.setMake(dto.getMake());
        vehicle.setReleaseYear(dto.getReleaseYear());
        vehicle.setColor(dto.getColor());
        vehicle.setSold(dto.getSold());
        vehicle.setUpdated(LocalDateTime.now());

        return repository.save(vehicle);
    }

    public Vehicle updateSomeVehicleInfo(Long id, Vehicle dto) {
        Vehicle vehicle = repository.findById(id).get();

        if (dto.getMake() != null && !vehicle.validMakesCar(dto.getMake()))
            return null;

        vehicle.setModel(dto.getModel() == null ? vehicle.getModel() : dto.getModel());
        vehicle.setMake(dto.getMake() == null ? vehicle.getMake() : dto.getMake());
        vehicle.setReleaseYear(dto.getReleaseYear() == null ? vehicle.getReleaseYear() : dto.getReleaseYear());
        vehicle.setColor(dto.getColor() == null ? vehicle.getColor() : dto.getColor());
        vehicle.setSold(dto.getSold() == null ? vehicle.getSold() : dto.getSold());
        vehicle.setUpdated(LocalDateTime.now());

        return repository.save(vehicle);
    }

    public void removeVehicle(Long id) {
        repository.deleteById(id);
    }
}
