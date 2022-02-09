package com.quickwait.hospital.domain.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hospital {

	private String id;
	
	private String name;
	
	private String address;
	
	private Double latitude;
	
	private Double longitude;
	
	private Double distance;
	
	private Double timeInSeconds;
	
	@JsonFormat(pattern = "hh:mm a")
	private LocalDateTime arrivalTime;
}
