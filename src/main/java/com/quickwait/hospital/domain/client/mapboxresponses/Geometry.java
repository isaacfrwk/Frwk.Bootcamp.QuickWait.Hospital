package com.quickwait.hospital.domain.client.mapboxresponses;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geometry {

	private String type;
	
	private List<List<Double>> coordinates;
}
