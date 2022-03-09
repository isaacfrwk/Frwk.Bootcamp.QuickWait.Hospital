package com.quickwait.hospital.api.v1.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.quickwait.hospital.domain.client.mapboxresponses.PathCoordinates;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HospitalControllerTest {

	@LocalServerPort
	private int port;
	
	@Value("${mapbox.hospitals-limit}")
	private Integer hospitalsLimit;
	
	@Test
	void should_return_200_ok() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
				.param("longitude", -43.944186709841304)
				.param("latitude", -19.950449118559817)
				.param("radix", 4.0)
				.basePath("/v1/nearest-hospitals")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("id", Matchers.hasSize(hospitalsLimit));
	}
	
	@Test
	void should_return_path_coordinates() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		ExtractableResponse<Response> response = RestAssured.given()
				.param("sourceLongitude", -43.9441920245059)
				.param("sourceLatitude", -19.95045684796272)
				.param("destinyLongitude", -43.95008453960092)
				.param("destinyLatitude", -19.941975477631484)
				.basePath("/v1/nearest-hospitals/coordinates")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value())
				.extract();
		
		PathCoordinates pathCoordinates = response.as(PathCoordinates.class);
		assertNotNull(pathCoordinates);
	}
}
