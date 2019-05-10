package com.pragma.api.v1;

import com.pragma.api.v1.dto.ResultValidationDto;
import com.pragma.api.v1.dto.ValidationTemperatureDto;
import com.pragma.exception.BeerTemeratureException;
import com.pragma.validator.BeerValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BeerApi implements V1 {

    private final Map<String, BeerValidator> mapBeerValidator;

    public BeerApi(Map<String, BeerValidator> mapBeerValidator) {
        this.mapBeerValidator = mapBeerValidator;
    }

    @PostMapping("/beers/{beerName}/validations")
    public ResponseEntity<?> validateTemperature(@PathVariable("beerName") String beerName,
                                                @RequestBody ValidationTemperatureDto validationTemperatureDto) {
        ResultValidationDto resultValidationDto = new ResultValidationDto();
        try {
            BeerValidator beerValidator = mapBeerValidator.get(beerName.toLowerCase());
            beerValidator.validateTemperature(validationTemperatureDto);
            resultValidationDto.setMessage("Everything is ok my consagrado");
            return ResponseEntity.ok(resultValidationDto);
        } catch (BeerTemeratureException e) {
            resultValidationDto.setMessage("Gave brete in temperature");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(resultValidationDto);
        }
    }
}
