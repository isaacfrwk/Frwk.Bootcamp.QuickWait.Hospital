package com.quickwait.hospital.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

	private String id;
	
	private String street;
	
	private String completeAddress;
	
	private Double longitude;
	
	private Double latitude;
}
