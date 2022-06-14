package com.example.kafka.clients.mqtt;

import com.example.kafka.clients.mapbox.MapBoxClient;
import com.example.kafka.controller.TelemetryDataController;
import com.example.kafka.controller.VehicleController;
import com.example.kafka.mappers.MapBoxMapper;
import com.example.kafka.entity.TelemetryDataEntity;
import com.example.kafka.entity.VehicleEntity;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttBeans {

    private static final String VEHICLES_CAR_1 = "vehicles/car1";
    private static final String VEHICLES_CAR_2 = "vehicles/car2";
    private static final String VEHICLES_CAR_3 = "vehicles/car3";
    private static final String VEHICLES_CAR_4 = "vehicles/car4";
    private static final String VEHICLES_CAR_1_TELE = "vehicles/car1/tele";
    private static final String VEHICLES_CAR_2_TELE = "vehicles/car2/tele";
    private static final String VEHICLES_CAR_3_TELE = "vehicles/car3/tele";
    private static final String VEHICLES_CAR_4_TELE = "vehicles/car4/tele";
    private static final String DEFAULT = "Default";
    @Autowired
    VehicleController vehicleController;
    @Autowired
    TelemetryDataController telemetryDataController;

    MapBoxClient mapBoxClient = new MapBoxClient();

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        options.setServerURIs(new String[] {"tcp://localhost:1883"});
        options.setCleanSession(true);

        factory.setConnectionOptions(options);

        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("serverIn",
                mqttClientFactory(), "#");

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
            switch (topic) {
                case VEHICLES_CAR_1:
                case VEHICLES_CAR_3:
                case VEHICLES_CAR_2:
                case VEHICLES_CAR_4:
                    addVehicle(message);
                    break;
                case VEHICLES_CAR_1_TELE:
                case VEHICLES_CAR_2_TELE:
                case VEHICLES_CAR_3_TELE:
                case VEHICLES_CAR_4_TELE:
                    addTelemetryData(message);
                    break;
                default:
                    System.out.println(DEFAULT);
            }
        };
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    private void addVehicle(Message message) {
        VehicleEntity vehicle = (VehicleEntity) jsonFormat(message, VehicleEntity.class);
        vehicle.setCode(topic(message));

        if (vehicleController.getVehicleByCode(topic(message)) == null) {
            vehicleController.add(vehicle);
        }

        System.out.println(vehicle.toString());
    }

    private void addTelemetryData(Message message) {

        System.out.println(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString() + " telemetry");
        TelemetryDataEntity telemetryData = (TelemetryDataEntity) jsonFormat(message, TelemetryDataEntity.class);
        mappingValuesFromMapBoxResponse(telemetryData, topic(message));

        if (telemetryData.getLongitude() != null) {
            telemetryDataController.add(telemetryData);
        }
        System.out.println(telemetryData.toString());
    }

    private String splitTopic(String topic) {
        String[] partsTopic = topic.split("/");
        return partsTopic[1];
    }

    private String topic(Message message) {
        return splitTopic(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString());
    }

    private Object jsonFormat(Message message, Class classToFormat) {
        Gson g = new Gson();
        return g.fromJson(message.getPayload().toString(), classToFormat);
    }

    private TelemetryDataEntity mappingValuesFromMapBoxResponse(TelemetryDataEntity telemetryData, String topic) {

        Gson g = new Gson();
        String mapBoxClientResponse = mapBoxClient.main(telemetryData.getLongitude(), telemetryData.getLatitude());
        MapBoxMapper mapBoxResponse = g.fromJson(mapBoxClientResponse, MapBoxMapper.class);

        String street = mapBoxResponse.getFeatures().get(0).getText();
        String houseNumber = mapBoxResponse.getFeatures().get(0).getAddress();
        String postCode = mapBoxResponse.getFeatures().get(0).getContext().get(0).getText();
        String city = mapBoxResponse.getFeatures().get(0).getContext().get(2).getText();

        telemetryData.setVehicle(vehicleController.getVehicleByCode(topic));
        telemetryData.setStreet(street);
        telemetryData.setHouseNumber(houseNumber);
        telemetryData.setPostCode(postCode);
        telemetryData.setCity(city);
        return telemetryData;
    }
}