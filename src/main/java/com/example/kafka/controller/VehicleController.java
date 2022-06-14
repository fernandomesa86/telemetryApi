package com.example.kafka.controller;

import java.util.List;
import com.example.kafka.model.Vehicle;
import com.example.kafka.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/vehicle")
    public void add(@RequestBody Vehicle newVehicle) {
        vehicleService.add(newVehicle);
    }

    @GetMapping("/vehicle/all")
    public List<Vehicle> getAll() {
        return vehicleService.getAll();
    }

    @GetMapping("/vehicle/{code}")
    public Vehicle getVehicleByCode(@PathVariable String code) {
        return vehicleService.getVehicleByCode(code);
    }
}
