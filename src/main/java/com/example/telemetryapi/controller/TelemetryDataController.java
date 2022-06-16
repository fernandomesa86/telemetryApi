package com.example.telemetryapi.controller;

import java.util.List;
import java.util.Optional;
import com.example.telemetryapi.exception.VehicleNotfoundException;
import com.example.telemetryapi.entity.TelemetryDataEntity;
import com.example.telemetryapi.entity.VehicleEntity;
import com.example.telemetryapi.services.TelemetryDataService;
import com.example.telemetryapi.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telemetryData")
public class TelemetryDataController {

    @Autowired
    TelemetryDataService telemetryDataService;

    @Autowired
    VehicleService vehicleService;

    @PostMapping()
    public void add(@RequestBody TelemetryDataEntity newTelemetryData) {
        telemetryDataService.add(newTelemetryData);
    }

    @GetMapping("/all")
    public List<TelemetryDataEntity> getAll() {
        return telemetryDataService.getAll();
    }

    @GetMapping("/{telemetryDataById}")
    public Optional<TelemetryDataEntity> getTelemetryDataById(@PathVariable int telemetryDataById) {
        return telemetryDataService.getTelemetryDataById(telemetryDataById);
    }

    @GetMapping("/vehicleCode/{vehicleCode}")
    public TelemetryDataEntity getLastPositionByVehicleId(@PathVariable String vehicleCode) {
        VehicleEntity vehicle = vehicleService.getVehicleByCode(vehicleCode);
        if (vehicle == null) {
            throw new VehicleNotfoundException();
        }
        List<TelemetryDataEntity> listTelemetryData = telemetryDataService.getLastPosition(vehicle.getVehicleId());
        return listTelemetryData.get(listTelemetryData.size() - 1);
    }
}