package com.pragma.unit;

import com.pragma.api.v1.dto.ValidationTemperatureDto;
import com.pragma.exception.BeerTemeratureException;
import com.pragma.validator.BeerValidator;
import com.pragma.validator.Pilsner;
import org.junit.Before;
import org.junit.Test;

public class PilsnerTest {

    private BeerValidator beerValidator;
    private ValidationTemperatureDto validationTemperatureDto;

    @Before
    public void setUp() {
        validationTemperatureDto = new ValidationTemperatureDto();
        beerValidator = new Pilsner();
    }

    @Test(expected = BeerTemeratureException.class)
    public void shouldValidateMinTemperature() throws BeerTemeratureException {
        validationTemperatureDto.setTemperature(-7d);
        beerValidator.validateTemperature(validationTemperatureDto);
    }

    @Test(expected = BeerTemeratureException.class)
    public void shouldValidateMaxTemperature() throws BeerTemeratureException {
        validationTemperatureDto.setTemperature(-3d);
        beerValidator.validateTemperature(validationTemperatureDto);
    }

    @Test
    public void shouldValidateRightTemperature() throws BeerTemeratureException {
        validationTemperatureDto.setTemperature(-5d);
        beerValidator.validateTemperature(validationTemperatureDto);
    }
}
