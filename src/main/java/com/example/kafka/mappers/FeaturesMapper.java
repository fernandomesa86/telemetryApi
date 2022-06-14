package com.example.kafka.mappers;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeaturesMapper {

    private String text;
    private String id;
    private String address;
    List<ContextMapper> context;
}
