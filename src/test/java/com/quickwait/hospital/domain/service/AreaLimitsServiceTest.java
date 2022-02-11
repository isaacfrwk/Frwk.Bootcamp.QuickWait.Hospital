package com.quickwait.hospital.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AreaLimitsServiceTest {

	@Autowired
	private AreaLimitsService areaLimitsService;
	
	@Test
	public void calcLatitudeAndLongitudeLimits() {
		Map<String, Double> areaLimits = areaLimitsService.calcLatitudeAndLongitudeLimits(-44.63615864397482, -20.539661678553465, 5.0); 
		
		assertEquals(-20.5848, areaLimits.get(AreaLimitsService.MIN_LATITUDE), 0.01);
		assertEquals(-44.2588, areaLimits.get(AreaLimitsService.MIN_LONGITUDE), 0.01);
		assertEquals(-20.4944, areaLimits.get(AreaLimitsService.MAX_LATITUDE), 0.01);
		assertEquals(-45.0135, areaLimits.get(AreaLimitsService.MAX_LONGITUDE), 0.01);
	}
}
