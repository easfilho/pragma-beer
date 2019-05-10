package com.pragma.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerTemperatureVerificationTest {

    @LocalServerPort
    private int randomPort;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = randomPort;
    }

    @Test
    public void shouldReturnValidTemperatureForPilsner() {
        RestAssured
        .given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\"temperature\": -5\n" +
                        "}")
                .post("/v1/beers/{beerName}/validations", "Pilsner")
                .then()
                .body("message", Matchers.equalTo("Everything is ok my consagrado"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldValidateInvalidTemperatureForPilsner() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\"temperature\": -8\n" +
                        "}")
                .post("/v1/beers/{beerName}/validations", "Pilsner")
                .then()
                .body("message", Matchers.equalTo("Gave brete in temperature"))
                .statusCode(HttpStatus.EXPECTATION_FAILED.value());
    }

    @Test
    public void shouldReturnValidTemperatureForIPA() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\"temperature\": -5.2\n" +
                        "}")
                .post("/v1/beers/{beerName}/validations", "IPA")
                .then()
                .body("message", Matchers.equalTo("Everything is ok my consagrado"))
                .statusCode(HttpStatus.OK.value());
    }
}
