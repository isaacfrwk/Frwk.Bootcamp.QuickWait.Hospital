package com.quickwait.hospital.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quickwait.hospital.api.v1.model.Feature;
import com.quickwait.hospital.domain.model.Address;
import com.quickwait.hospital.domain.model.Hospital;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Feature.class, Hospital.class)
			.addMapping(Feature::getText, Hospital::setName)
			.addMapping(Feature::getPlaceName, Hospital::setAddress);
		
		modelMapper.createTypeMap(Feature.class, Address.class)
			.addMapping(Feature::getText, Address::setStreet)
			.addMapping(Feature::getPlaceName, Address::setCompleteAddress);
		
		return modelMapper;
	}
}
