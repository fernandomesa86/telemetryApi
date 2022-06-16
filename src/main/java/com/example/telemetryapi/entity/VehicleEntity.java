package com.example.telemetryapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicleId")
    private Integer vehicleId;
    private String code;
    private String manufacturer;
    private String type;
    private String modell;
    private String engine_performance_kw;
    private String engine_performance_ps;
    private String fuel;
    private String seats;
    private String emission_class;
    private String registration;
    private int construction_year;
    private String vin;
    private String hsn;
    private String tsn;
    private String license_plate;
}
