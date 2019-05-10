package com.pragma.validator;

import com.pragma.api.v1.dto.ValidationTemperatureDto;
import com.pragma.exception.BeerTemeratureException;
import org.springframework.stereotype.Component;

@Component
public class Ipa implements BeerValidator {

    private static final Double MIN_TEMPERATURE = -6d;
    private static final Double MAX_TEMPERATURE = -5d;

    @Override
    public void validateTemperature(ValidationTemperatureDto validationTemperatureDto) throws BeerTemeratureException {
        if(validationTemperatureDto.getTemperature() < MIN_TEMPERATURE || validationTemperatureDto.getTemperature() > MAX_TEMPERATURE) {
            throw new BeerTemeratureException("Tá ruim a temperatura aí");
        }
    }
}
