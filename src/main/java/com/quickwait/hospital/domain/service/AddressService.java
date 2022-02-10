package com.quickwait.hospital.domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.quickwait.hospital.api.v1.model.MapboxResponseObject;
import com.quickwait.hospital.core.mapbox.MapboxProperties;
import com.quickwait.hospital.domain.model.Address;

@Service
public class AddressService {

	private final RestTemplate restTemplate;
	
	private final ModelMapper modelMapper;
	
	private final MapboxProperties mapboxProperties;
	
	public AddressService(RestTemplate restTemplate, ModelMapper modelMapper, MapboxProperties mapboxProperties) {
		this.restTemplate = Objects.requireNonNull(restTemplate);
		this.modelMapper = Objects.requireNonNull(modelMapper);
		this.mapboxProperties = Objects.requireNonNull(mapboxProperties);
	}
	
	public List<Address> getAddresses(String addressName) {
		MapboxResponseObject addressInputData = getAddressesInputData(addressName);
		
		return addressInputData.convertFeaturesToAddresses(modelMapper);
	}

	private MapboxResponseObject getAddressesInputData(String addressName) {
		String urlTemplate = mapboxProperties.getHost() + "/geocoding/v5/mapbox.places/{address}.json?types={types}"
				+ "&access_token={token}";
		
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("address", addressName);
		urlVariables.put("types", "address");
		urlVariables.put("token", mapboxProperties.getToken());
		
		return restTemplate.getForObject(urlTemplate, MapboxResponseObject.class, urlVariables);
	}
}
