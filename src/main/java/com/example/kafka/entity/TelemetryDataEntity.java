package com.example.kafka.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "telemetryData")
public class TelemetryDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "telemetryDataId")
    private int telemetryDataId;
    private String latitude;
    private String longitude;
    private String accuracy_m;
    private String altitude_m;
    private String speed_m_per_s;
    private String bearing_deg;
    private String sat_used;
    private String sat_inview;
    private String fuel_percent;
    private String battery_percent;
    private String lights;
    private String engine;
    private String street;
    private String postCode;
    private String houseNumber;
    private String city;

    @ManyToOne
    @JoinColumn(name = "vehicle_vehicle_id", nullable = false, updatable = true)
    private VehicleEntity vehicle;
}
