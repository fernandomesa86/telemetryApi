package com.example.kafka.service;

import java.util.List;
import java.util.Optional;
import com.example.kafka.model.Vehicle;
import com.example.kafka.respositories.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class VehicleService {

    @Autowired
    VehiclesRepository vehiclesRepository;

    public void add(@RequestBody Vehicle newVehicle) {
        vehiclesRepository.save(newVehicle);
    }

    public List<Vehicle> getAll() {
        return vehiclesRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(int vehicleId) {
        return vehiclesRepository.findById(vehicleId);
    }

    public Vehicle getVehicleByCode(String code) {
        return vehiclesRepository.getVehicleByCode(code);
    }
}
