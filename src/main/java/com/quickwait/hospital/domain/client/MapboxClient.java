package com.quickwait.hospital.domain.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.quickwait.hospital.api.v1.model.MapboxResponseObject;
import com.quickwait.hospital.api.v1.model.PathCoordinates;
import com.quickwait.hospital.core.mapbox.MapboxProperties;
import com.quickwait.hospital.domain.service.AreaLimitsService;

@Service
public class MapboxClient {

	private final RestTemplate restTemplate;

	private final MapboxProperties mapboxProperties;

	public MapboxClient(RestTemplate restTemplate, MapboxProperties mapboxProperties) {
		this.restTemplate = Objects.requireNonNull(restTemplate);
		this.mapboxProperties = Objects.requireNonNull(mapboxProperties);
	}

	public MapboxResponseObject getAddressesInputData(String addressName) {
		String urlTemplate = mapboxProperties.getHost() + "/geocoding/v5/mapbox.places/{address}.json"
				+ "?types={types}&access_token={token}";
		
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("address", addressName);
		urlVariables.put("types", "address");
		urlVariables.put("token", mapboxProperties.getToken());
		
		return restTemplate.getForObject(urlTemplate, MapboxResponseObject.class, urlVariables);
	}

	public PathCoordinates getPathCoordinates(Double originLongitude, Double originLatitude, Double destinyLongitude,
			Double destinyLatitude) {
		String url = mapboxProperties.getHost() + "/directions/v5/mapbox/driving/"
				+ "{originLongitude},{originLatitude};{destinyLongitude},{destinyLatitude}"
				+ "?overview={overview}&geometries={geometries}&access_token={token}";

		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("originLongitude", originLongitude.toString());
		urlVariables.put("originLatitude", originLatitude.toString());
		urlVariables.put("destinyLongitude", destinyLongitude.toString());
		urlVariables.put("destinyLatitude", destinyLatitude.toString());
		urlVariables.put("overview", "full");
		urlVariables.put("geometries", "geojson");
		urlVariables.put("token", mapboxProperties.getToken());

		return restTemplate.getForObject(url, PathCoordinates.class, urlVariables);
	}

	public MapboxResponseObject getHospitalInputData(Map<String, Double> areaLimits) {
		String url = mapboxProperties.getHost()
				+ "/geocoding/v5/mapbox.places/hospital.json?types={types}&limit={limit}"
				+ "&bbox={minLongitude},{minLatitude},{maxLongitude},{maxLatitude}&access_token={token}";
		
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("types", "poi");
		urlVariables.put("limit", mapboxProperties.getHospitalsLimit());
		urlVariables.put("minLongitude", areaLimits.get(AreaLimitsService.MIN_LONGITUDE).toString());
		urlVariables.put("minLatitude", areaLimits.get(AreaLimitsService.MIN_LATITUDE).toString());
		urlVariables.put("maxLongitude", areaLimits.get(AreaLimitsService.MAX_LONGITUDE).toString());
		urlVariables.put("maxLatitude", areaLimits.get(AreaLimitsService.MAX_LATITUDE).toString());
		urlVariables.put("token", mapboxProperties.getToken());

		return restTemplate.getForObject(url, MapboxResponseObject.class, urlVariables);
	}
}
