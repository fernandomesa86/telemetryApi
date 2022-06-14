package com.example.kafka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "telemetryData")
public class TelemetryData {
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
    private String lights;
    private String engine;
    private String street;
    private String house;

    @ManyToOne
    @JoinColumn(name = "vehicle_vehicle_id", nullable = false, updatable = true)
    private Vehicle vehicle;
}