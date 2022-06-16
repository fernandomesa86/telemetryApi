package com.example.telemetryapi.controller;

import java.util.List;
import java.util.Optional;
import com.example.telemetryapi.entity.VehicleEntity;
import com.example.telemetryapi.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping()
    public void add(@RequestBody VehicleEntity newVehicle) {
        vehicleService.add(newVehicle);
    }

    @GetMapping("/all")
    public List<VehicleEntity> getAll() {
        return vehicleService.getAll();
    }

    @GetMapping("/code/{code}")
    public VehicleEntity getVehicleByCode(@PathVariable String code) {
        return vehicleService.getVehicleByCode(code);
    }

    @GetMapping("/{vehicleId}")
    public Optional<VehicleEntity> getVehicleById(@PathVariable int vehicleId) {
        return vehicleService.getVehicleById(vehicleId);
    }
}
