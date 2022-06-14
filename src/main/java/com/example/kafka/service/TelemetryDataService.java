package com.example.kafka.service;

import java.util.List;
import java.util.Optional;
import com.example.kafka.model.TelemetryData;
import com.example.kafka.respositories.TelemetryDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TelemetryDataService {

    @Autowired
    TelemetryDataRepository telemetryDataRepository;

    public void add(@RequestBody TelemetryData newTelemetryData) {
        telemetryDataRepository.save(newTelemetryData);
    }

    public List<TelemetryData> getAll() {
        return telemetryDataRepository.findAll();
    }

    public Optional<TelemetryData> getTelemetryDataById(int telemetryDataId) {
        return telemetryDataRepository.findById(telemetryDataId);
    }
}
