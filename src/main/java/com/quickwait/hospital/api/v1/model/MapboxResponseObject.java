package com.quickwait.hospital.api.v1.model;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.quickwait.hospital.domain.model.Address;
import com.quickwait.hospital.domain.model.Hospital;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapboxResponseObject {

	private List<Feature> features;
	
	public List<Address> convertFeaturesToAddresses(ModelMapper modelMapper) {
		if(this.getFeatures() == null || this.getFeatures().isEmpty()) return null;
		
		List<Address> addresses = new ArrayList<Address>();
		
		this.getFeatures().forEach(feature -> {
			Address address = new Address();
			modelMapper.map(feature, address);
			
			if(feature.getCenter() != null && feature.getCenter().size() >= 2) {
				address.setLongitude(feature.getCenter().get(0));
				address.setLatitude(feature.getCenter().get(1));
			}
			
			addresses.add(address);
		});
		
		return addresses;
	}
	
	public List<Hospital> convertFeaturesToHospitals(ModelMapper modelMapper) {
		if(this.getFeatures() == null || this.getFeatures().isEmpty()) return null;
		
		List<Hospital> hospitals = new ArrayList<Hospital>();

		this.getFeatures().forEach(feature -> {
			Hospital hospital = new Hospital();
			modelMapper.map(feature, hospital);

			if(feature.getCenter() != null && feature.getCenter().size() >= 2) {
				hospital.setLongitude(feature.getCenter().get(0));
				hospital.setLatitude(feature.getCenter().get(1));
			}
			
			hospitals.add(hospital);
		});
		
		return hospitals;
	}
}
