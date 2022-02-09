package com.quickwait.hospital.domain.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.quickwait.hospital.api.v1.model.MapboxResponseObject;
import com.quickwait.hospital.api.v1.model.PathCoordinates;
import com.quickwait.hospital.domain.model.Hospital;

@Service
public class NearestHospitalsService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AreaLimitsService areaLimitsService;
	
	@Value("${mapbox.host}")
	private String mapboxHost;
	
	@Value("${mapbox.token}")
	private String mapboxToken;
	
	@Value("${mapbox.hospitals.limit}")
	private String mapboxHospitalsLimit;
	
	public List<Hospital> getNearestHospitals(Double longitude, Double latitude, Double radix) {
		Map<String, Double> areaLimits = areaLimitsService.calcLatitudeAndLongitudeLimits(longitude, latitude, radix);
		
		MapboxResponseObject hospitalInputData = getHospitalInputData(areaLimits);
		
		List<Hospital> hospitals = hospitalInputData.convertFeaturesToHospitals(modelMapper);
		
		if(hospitals == null) return null;
		
		hospitals.forEach(hospital -> {
			PathCoordinates pathCoordinates = getPathCoordinates(longitude, latitude, 
									hospital.getLongitude(), hospital.getLatitude());
			
			if(pathCoordinates.getRoutes() != null && !pathCoordinates.getRoutes().isEmpty()) {
				hospital.setDistance(pathCoordinates.getRoutes().get(0).getDistance());
				hospital.setTimeInSeconds(pathCoordinates.getRoutes().get(0).getDuration());
				hospital.setArrivalTime(LocalDateTime.now().plusSeconds(hospital.getTimeInSeconds().longValue()));
			}
		});
		
		return hospitals;
	}
	
	public PathCoordinates getPathCoordinates(Double originLongitude, Double originLatitude, 
							Double destinyLongitude, Double destinyLatitude) {
		String url = mapboxHost + "/directions/v5/mapbox/driving/"
				+ "{originLongitude},{originLatitude};{destinyLongitude},{destinyLatitude}"
				+ "?overview={overview}&geometries={geometries}&language={language}"
				+ "&access_token={token}";
		
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("originLongitude", originLongitude.toString());
		urlVariables.put("originLatitude", originLatitude.toString());
		urlVariables.put("destinyLongitude", destinyLongitude.toString());
		urlVariables.put("destinyLatitude", destinyLatitude.toString());
		urlVariables.put("overview", "full");
		urlVariables.put("geometries", "geojson");
		urlVariables.put("language", "pt-BR");
		urlVariables.put("token", mapboxToken);
		
		return restTemplate.getForObject(url, PathCoordinates.class, urlVariables);
	}

	private MapboxResponseObject getHospitalInputData(Map<String, Double> areaLimits) {
		String url = mapboxHost + "/geocoding/v5/mapbox.places/hospital.json?types={types}&limit={limit}"
				+ "&bbox={minLongitude},{minLatitude},{maxLongitude},{maxLatitude}"
				+ "&access_token={token}";
		
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("types", "poi");
		urlVariables.put("limit", mapboxHospitalsLimit);
		urlVariables.put("minLongitude", areaLimits.get(AreaLimitsService.MIN_LONGITUDE).toString());
		urlVariables.put("minLatitude", areaLimits.get(AreaLimitsService.MIN_LATITUDE).toString());
		urlVariables.put("maxLongitude", areaLimits.get(AreaLimitsService.MAX_LONGITUDE).toString());
		urlVariables.put("maxLatitude", areaLimits.get(AreaLimitsService.MAX_LATITUDE).toString());
		urlVariables.put("token", mapboxToken);
		
		return restTemplate.getForObject(url, MapboxResponseObject.class, urlVariables);
	}
	
}
