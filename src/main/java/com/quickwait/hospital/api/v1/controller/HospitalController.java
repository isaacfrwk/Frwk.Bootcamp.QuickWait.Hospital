package com.quickwait.hospital.api.v1.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

	private final NearestHospitalsService nearestHospitalsService;
	
	public HospitalController(NearestHospitalsService nearestHospitalsService) {
		this.nearestHospitalsService = Objects.requireNonNull(nearestHospitalsService);
	}

	@GetMapping
	public List<Hospital> getNearestHospitals(
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LONGITUDE) 
						@Max(AreaLimitsService.MAX_VALID_VALUE_LONGITUDE) @NotNull Double longitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LATITUDE) 
						@Max(AreaLimitsService.MAX_VALID_VALUE_LATITUDE) @NotNull Double latitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_RADIX) @NotNull Double radix) {
		
		return nearestHospitalsService.getNearestHospitals(longitude, latitude, radix);
	}
	
	@GetMapping("/coordinates")
	public PathCoordinates getPathCoordinates(
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LONGITUDE) 
						@Max(AreaLimitsService.MAX_VALID_VALUE_LONGITUDE) @NotNull Double sourceLongitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LATITUDE) 
						@Max(AreaLimitsService.MAX_VALID_VALUE_LATITUDE) @NotNull Double sourceLatitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LONGITUDE) 
						@Max(AreaLimitsService.MAX_VALID_VALUE_LONGITUDE) @NotNull Double destinyLongitude, 
					@RequestParam @Min(AreaLimitsService.MIN_VALID_VALUE_LATITUDE) 
						@Max(AreaLimitsService.MAX_VALID_VALUE_LATITUDE) @NotNull Double destinyLatitude) {
		return nearestHospitalsService.getPathCoordinates(sourceLongitude, sourceLatitude, 
					destinyLongitude, destinyLatitude);
	}
}
