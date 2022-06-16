package com.example.telemetryapi.clients.mqtt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.example.telemetryapi.clients.mapbox.MapBoxClient;
import com.example.telemetryapi.controller.VehicleController;
import com.example.telemetryapi.entity.TelemetryDataEntity;
import com.example.telemetryapi.entity.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MqttBeansTest {

    private VehicleEntity vehicleEntity;
    public static final String CAR_5 = "car5";
    public static final String LATITUDE = "13.111111111";
    public static final String LONGITUDE = "12.11111111";

    @InjectMocks
    MqttBeans mqttBeans;

    @Mock
    VehicleController vehicleController;

    @Mock
    MapBoxClient mapBoxClientResponse;

    @Test
    void mappingValuesFromMapBox_ReturnTelemetryData() {

        given(vehicleController.getVehicleByCode(CAR_5)).willReturn(vehicleEntity);
        given(mapBoxClientResponse.getGeoLocation(LONGITUDE, LATITUDE)).willReturn(response);

        TelemetryDataEntity result = mqttBeans.mappingValuesFromMapBoxResponse(buildTelemetryDataEntity(), CAR_5);
        assertEquals("Berlin", result.getCity());
        assertEquals("36", result.getHouseNumber());
        assertEquals("10117", result.getPostCode());
        assertEquals("Leipziger Straße", result.getStreet());
    }

    private TelemetryDataEntity buildTelemetryDataEntity() {
        return TelemetryDataEntity.builder().telemetryDataId(1)
                .longitude("12.11111111")
                .latitude("13.111111111").build();
    }


    String response = "{\n" +
            "  \"type\": \"FeatureCollection\",\n" +
            "  \"query\": [\n" +
            "    13.39198335,\n" +
            "    52.51054491\n" +
            "  ],\n" +
            "  \"features\": [\n" +
            "    {\n" +
            "      \"id\": \"address.8908038197075562\",\n" +
            "      \"type\": \"Feature\",\n" +
            "      \"place_type\": [\n" +
            "        \"address\"\n" +
            "      ],\n" +
            "      \"relevance\": 1,\n" +
            "      \"properties\": {\n" +
            "        \"accuracy\": \"rooftop\"\n" +
            "      },\n" +
            "      \"text\": \"Leipziger Straße\",\n" +
            "      \"place_name\": \"Leipziger Straße 36, 10117 Berlin, Germany\",\n" +
            "      \"center\": [\n" +
            "        13.39220415,\n" +
            "        52.51034465\n" +
            "      ],\n" +
            "      \"geometry\": {\n" +
            "        \"type\": \"Point\",\n" +
            "        \"coordinates\": [\n" +
            "          13.39220415,\n" +
            "          52.51034465\n" +
            "        ]\n" +
            "      },\n" +
            "      \"address\": \"36\",\n" +
            "      \"context\": [\n" +
            "        {\n" +
            "          \"id\": \"postcode.7643378846943780\",\n" +
            "          \"text\": \"10117\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"locality.10015544438524990\",\n" +
            "          \"wikidata\": \"Q2013767\",\n" +
            "          \"text\": \"Mitte\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"place.14094307404564380\",\n" +
            "          \"short_code\": \"DE-BE\",\n" +
            "          \"wikidata\": \"Q64\",\n" +
            "          \"text\": \"Berlin\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"country.10814856728480410\",\n" +
            "          \"wikidata\": \"Q183\",\n" +
            "          \"short_code\": \"de\",\n" +
            "          \"text\": \"Germany\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"attribution\": \"NOTICE: © 2022 Mapbox and its suppliers. All rights reserved. Use of this data is subject to the Mapbox Terms of Service (https://www.mapbox.com/about/maps/). This response and the information it contains may not be retained. POI(s) provided by Foursquare.\"\n" +
            "}";
}