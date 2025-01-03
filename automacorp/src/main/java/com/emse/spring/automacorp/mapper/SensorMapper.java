package com.emse.spring.automacorp.mapper;
import com.emse.spring.automacorp.dto.SensorDto;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import com.emse.spring.automacorp.record.Sensor;

public class SensorMapper {
    public static Sensor of (SensorEntity sensor) {
        return new Sensor(
                sensor.getId(),
                sensor.getName(),
                sensor.getValue(),
                sensor.getSensorType()
        );
    }
}

//public class SensorMapper {
//    public static Sensor of(SensorEntity sensor) {
//        return new Sensor(
//                sensor.getId(),
//                sensor.getName(),
//                sensor.getValue(),
//                sensor.getSensorType()
//        );
//    }
//}