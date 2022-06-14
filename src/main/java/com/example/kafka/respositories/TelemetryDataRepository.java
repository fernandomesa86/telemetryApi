package com.example.kafka.respositories;

import com.example.kafka.model.TelemetryData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelemetryDataRepository extends JpaRepository<TelemetryData, Integer> {
}
