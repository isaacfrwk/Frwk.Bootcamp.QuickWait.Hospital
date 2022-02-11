package com.quickwait.hospital.domain.service;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.quickwait.hospital.api.v1.model.MapboxResponseObject;
import com.quickwait.hospital.domain.client.MapboxClient;
import com.quickwait.hospital.domain.model.Address;

@Service
public class AddressService {

	private final ModelMapper modelMapper;
	
	private final MapboxClient mapboxClient;
	
	public AddressService(ModelMapper modelMapper, MapboxClient mapboxClient) {
		this.modelMapper = Objects.requireNonNull(modelMapper);
		this.mapboxClient = Objects.requireNonNull(mapboxClient);
	}
	
	public List<Address> getAddresses(String addressName) {
		MapboxResponseObject addressInputData = mapboxClient.getAddressesInputData(addressName);
		
		return addressInputData.convertFeaturesToAddresses(modelMapper);
	}

}
