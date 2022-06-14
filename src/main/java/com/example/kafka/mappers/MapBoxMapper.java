package com.example.kafka.mappers;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MapBoxMapper {

    private List<FeaturesMapper> features;
    private String type;
}
