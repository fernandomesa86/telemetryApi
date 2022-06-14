package com.example.kafka.respositories;

import com.example.kafka.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehiclesRepository extends JpaRepository<VehicleEntity, Integer> {

    @Query("select v from VehicleEntity v where v.code like %?1%")
    VehicleEntity getVehicleByCode(String code);
}
