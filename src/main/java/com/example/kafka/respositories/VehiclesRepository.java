package com.example.kafka.respositories;

import com.example.kafka.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehiclesRepository extends JpaRepository<Vehicle, Integer> {

    @Query("select v from Vehicle v where v.code like %?1%")
    Vehicle getVehicleByCode(String code);
}
