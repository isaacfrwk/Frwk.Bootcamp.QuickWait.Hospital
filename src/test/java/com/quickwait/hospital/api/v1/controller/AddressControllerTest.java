package com.quickwait.hospital.api.v1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.quickwait.hospital.domain.model.Address;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AddressControllerTest {

	@LocalServerPort
	private int port;
	
	@Test
	public void should_return_five_addresses() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		ExtractableResponse<Response> extract = RestAssured.given()
				.param("addressName", "Rua Luiza Carvalho Torres")
				.basePath("/v1/address")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value())
				.extract();
		
		Address[] addresses = extract.as(Address[].class);
		
		assertEquals(5, addresses.length);
	}
	
	@Test
	public void should_return_bad_request_due_blank_parameter() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
				.param("addressName", "   ")
				.basePath("/v1/address")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.BAD_REQUEST.value());
	}
}
