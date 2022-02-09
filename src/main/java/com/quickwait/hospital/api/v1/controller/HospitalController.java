package com.quickwait.hospital.api.v1.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quickwait.hospital.api.v1.model.PathCoordinates;
import com.quickwait.hospital.domain.model.Hospital;
import com.quickwait.hospital.domain.service.AreaLimitsService;
import com.quickwait.hospital.domain.service.NearestHospitalsService;

@RestController
@RequestMapping("/v1/nearest-hospitals")
@Validated
public class HospitalController {

	@Autowired
	private NearestHospitalsService nearestHospitalsService;
	
	@GetMapping
	public List<Hospital> getNearestHospitals(
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LONGITUDE) 
									@Max(AreaLimitsService.MAX_VALID_VALUE_LONGITUDE) Double longitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LATITUDE) 
									@Max(AreaLimitsService.MAX_VALID_VALUE_LATITUDE) Double latitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_RADIX) Double radix) {
		return nearestHospitalsService.getNearestHospitals(longitude, latitude, radix);
	}
	
	@GetMapping("/coordinates")
	public PathCoordinates getPathCoordinates(
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LONGITUDE) 
								@Max(AreaLimitsService.MAX_VALID_VALUE_LONGITUDE) Double sourceLongitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LATITUDE) 
								@Max(AreaLimitsService.MAX_VALID_VALUE_LATITUDE) Double sourceLatitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LONGITUDE) 
								@Max(AreaLimitsService.MAX_VALID_VALUE_LONGITUDE) Double destinyLongitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LATITUDE) 
								@Max(AreaLimitsService.MAX_VALID_VALUE_LATITUDE) Double destinyLatitude) {
		return nearestHospitalsService.getPathCoordinates(sourceLongitude, sourceLatitude, 
					destinyLongitude, destinyLatitude);
	}
}
