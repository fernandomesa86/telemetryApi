package com.example.kafka.services;

import java.util.List;
import java.util.Optional;
import com.example.kafka.entity.VehicleEntity;
import com.example.kafka.respositories.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class VehicleService {

    @Autowired
    VehiclesRepository vehiclesRepository;

    public void add(@RequestBody VehicleEntity newVehicle) {
        vehiclesRepository.save(newVehicle);
    }

    public List<VehicleEntity> getAll() {
        return vehiclesRepository.findAll();
    }

    public Optional<VehicleEntity> getVehicleById(int vehicleId) {
        return vehiclesRepository.findById(vehicleId);
    }

    public VehicleEntity getVehicleByCode(String code) {
        return vehiclesRepository.getVehicleByCode(code);
    }
}
