package com.tinnova.technical_assessment.controllers;

import com.tinnova.technical_assessment.dto.RequestDTO;
import com.tinnova.technical_assessment.dto.ResponseDTO;
import com.tinnova.technical_assessment.services.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private final VehicleServices services;

    @Autowired
    public VehicleController(VehicleServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllVehicles(
            @RequestParam(required = false) String make,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) String color) {
        return ResponseEntity.ok(services.getAllVehicleWithParametersOrNot(make, releaseYear, color));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(services.getVehicleById(id));
    }

    @GetMapping("/unsold")
    public ResponseEntity<ResponseDTO> getCountUnsoldVehicles() {
        return ResponseEntity.ok(services.getCountUnsoldVehicles());
    }

    @GetMapping("/decade/{decade}")
    public ResponseEntity<ResponseDTO> getCountVehiclesByDecade(@PathVariable Integer decade) {
        return ResponseEntity.ok(services.getCountVehiclesByDecade(decade));
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<ResponseDTO> getCountByMake(@PathVariable String make) {
        return ResponseEntity.ok(services.getCountByMake(make));
    }

    @GetMapping("/lastweek")
    public ResponseEntity<ResponseDTO> getAllRegisteredVehiclesAtLastWeek() {
        return ResponseEntity.ok(services.getAllRegisteredVehiclesAtLastWeek());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> registerVehicle(@RequestBody RequestDTO dto) {
        return ResponseEntity.ok().body(services.registerVehicle(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateAllVehicleInfo(@PathVariable Long id, @RequestBody RequestDTO dto) {
        return ResponseEntity.ok().body(services.updateAllVehicleInfo(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateSomeVehicleInfo(@PathVariable Long id, @RequestBody RequestDTO dto) {
        return ResponseEntity.ok().body(services.updateSomeVehicleInfo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> removeVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(services.removeVehicle(id));
    }
}
