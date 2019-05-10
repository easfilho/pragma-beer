package com.pragma.validator;

import com.pragma.api.v1.dto.ValidationTemperatureDto;
import com.pragma.exception.BeerTemeratureException;

public interface BeerValidator {

    void validateTemperature(ValidationTemperatureDto validationTemperatureDto) throws BeerTemeratureException;
}
