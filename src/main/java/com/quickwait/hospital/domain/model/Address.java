package com.quickwait.hospital.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {

	private String id;
	
	private String street;
	
	private String completeAddress;
	
	private Double longitude;
	
	private Double latitude;
}
