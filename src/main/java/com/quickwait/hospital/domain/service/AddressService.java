package com.quickwait.hospital.domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.quickwait.hospital.api.v1.model.MapboxResponseObject;
import com.quickwait.hospital.domain.model.Address;

@Service
public class AddressService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${mapbox.host}")
	private String mapboxHost;
	
	@Value("${mapbox.token}")
	private String mapboxToken;
	
	public List<Address> getAddresses(String addressName) {
		MapboxResponseObject addressInputData = getAddressesInputData(addressName);
		
		return addressInputData.convertFeaturesToAddresses(modelMapper);
	}

	private MapboxResponseObject getAddressesInputData(String addressName) {
		String urlTemplate = mapboxHost + "/geocoding/v5/mapbox.places/{address}.json?types={types}"
				+ "&access_token={token}";
		
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("address", addressName);
		urlVariables.put("types", "address");
		urlVariables.put("token", mapboxToken);
		
		return restTemplate.getForObject(urlTemplate, MapboxResponseObject.class, urlVariables);
	}
}
