package com.example.kafka.controller;

import java.util.List;
import java.util.Optional;
import com.example.kafka.model.TelemetryData;
import com.example.kafka.service.TelemetryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TelemetryDataController {

    @Autowired
    TelemetryDataService telemetryDataService;

    @PostMapping("/telemetryData")
    public void add(@RequestBody TelemetryData newTelemetryData) {
        telemetryDataService.add(newTelemetryData);
    }

    @GetMapping("/telemetryData/all")
    public List<TelemetryData> getAll() {
        return telemetryDataService.getAll();
    }

    @GetMapping("/telemetryData/{telemetryDataById}")
    public Optional<TelemetryData> getTelemetryDataById(@PathVariable int telemetryDataById) {
        return telemetryDataService.getTelemetryDataById(telemetryDataById);
    }
}
