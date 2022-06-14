package com.example.kafka.services;

import java.util.List;
import java.util.Optional;
import com.example.kafka.entity.TelemetryDataEntity;
import com.example.kafka.respositories.TelemetryDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TelemetryDataService {

    @Autowired
    TelemetryDataRepository telemetryDataRepository;

    public void add(@RequestBody TelemetryDataEntity newTelemetryData) {
        telemetryDataRepository.save(newTelemetryData);
    }

    public List<TelemetryDataEntity> getAll() {
        return telemetryDataRepository.findAll();
    }

    public Optional<TelemetryDataEntity> getTelemetryDataById(int telemetryDataId) {
        return telemetryDataRepository.findById(telemetryDataId);
    }

    public List<TelemetryDataEntity> getLastPosition(int vehicleId) {
        return telemetryDataRepository.getLastPosition(vehicleId);
    }
}
