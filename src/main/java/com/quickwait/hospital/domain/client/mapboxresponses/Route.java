package com.quickwait.hospital.domain.client.mapboxresponses;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Route {

	private Double duration;
	
	private Double distance;
	
	@JsonAlias({"weight_name"})
	private String weightName;
	
	private Double weight;
	
	private Geometry geometry;
}
