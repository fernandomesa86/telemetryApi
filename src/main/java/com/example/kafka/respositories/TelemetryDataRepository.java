package com.example.kafka.respositories;

import java.util.List;
import com.example.kafka.entity.TelemetryDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TelemetryDataRepository extends JpaRepository<TelemetryDataEntity, Integer> {

    @Query("select td from TelemetryDataEntity td where td.vehicle.vehicleId = ?1")
    List<TelemetryDataEntity> getLastPosition(int vehicleId);
}
