package com.quickwait.hospital.api.v1.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geometry {

	private String type;
	
	private List<List<Double>> coordinates;
}
