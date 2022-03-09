package com.quickwait.hospital.domain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.quickwait.hospital.domain.client.MapboxClient;
import com.quickwait.hospital.domain.client.mapboxresponses.MapboxResponseObject;
import com.quickwait.hospital.domain.client.mapboxresponses.PathCoordinates;
import com.quickwait.hospital.domain.model.Hospital;

@Service
public class NearestHospitalsService {

	private final ModelMapper modelMapper;
	
	private final AreaLimitsService areaLimitsService;
	
	private final MapboxClient mapboxClient;

	public NearestHospitalsService(ModelMapper modelMapper, MapboxClient mapboxClient,
									AreaLimitsService areaLimitsService) {
		this.modelMapper = Objects.requireNonNull(modelMapper);
		this.mapboxClient = Objects.requireNonNull(mapboxClient);
		this.areaLimitsService = Objects.requireNonNull(areaLimitsService);
	}
	
	public List<Hospital> getNearestHospitals(Double longitude, Double latitude, Double radix) {
		Map<String, Double> areaLimits = areaLimitsService.calcLatitudeAndLongitudeLimits(longitude, latitude, radix);
		
		MapboxResponseObject hospitalInputData = mapboxClient.getHospitalInputData(areaLimits);
		
		List<Hospital> hospitals = hospitalInputData.convertFeaturesToHospitals(modelMapper);
		
		if(Objects.isNull(hospitals) || hospitals.isEmpty()) return new ArrayList<>();
		
		hospitals.forEach(hospital -> {
			PathCoordinates pathCoordinates = mapboxClient.getPathCoordinates(longitude, latitude, 
									hospital.getLongitude(), hospital.getLatitude());
			
			if(Objects.nonNull(pathCoordinates.getRoutes()) && !pathCoordinates.getRoutes().isEmpty()) {
				hospital.setDistance(pathCoordinates.getRoutes().get(0).getDistance());
				hospital.setTimeInSeconds(pathCoordinates.getRoutes().get(0).getDuration());
				hospital.setArrivalTime(LocalDateTime.now().plusSeconds(hospital.getTimeInSeconds().longValue()));
			}
			
		});
		
		return hospitals;
	}
	
	public PathCoordinates getPathCoordinates(Double originLongitude, Double originLatitude, 
					Double destinyLongitude, Double destinyLatitude) {
		return mapboxClient.getPathCoordinates(originLongitude, originLatitude, destinyLongitude, destinyLatitude);
	}
	
}
