package com.tinnova.technical_assessment.controllers;

import com.tinnova.technical_assessment.entities.Vehicle;
import com.tinnova.technical_assessment.services.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private final VehicleServices services;

    @Autowired
    public VehicleController(VehicleServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(services.getAllVehicle());
    }

    @GetMapping(params = {"make, releaseYear, color"})
    public ResponseEntity<List<Vehicle>> getAllVehiclesWithParameters(
            @RequestParam String make, @RequestParam Integer releaseYear, @RequestParam String color) {
        return ResponseEntity.ok(services.getAllVehicleWithParameters(make, releaseYear, color));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(services.getVehicleById(id));
    }

    @PostMapping
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle dto) {
        return ResponseEntity.ok().body(services.registerVehicle(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateAllVehicleInfo(@PathVariable Long id, @RequestBody Vehicle dto) {
        return ResponseEntity.ok().body(services.updateAllVehicleInfo(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehicle> updateSomeVehicleInfo(@PathVariable Long id, @RequestBody Vehicle dto) {
        return ResponseEntity.ok().body(services.updateSomeVehicleInfo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeVehicle(@PathVariable Long id) {
        services.removeVehicle(id);
        return ResponseEntity.ok(null);
    }
}
