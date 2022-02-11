package com.quickwait.hospital.api.v1.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feature {

	private String id;
	private String type;
	private Integer relevance;
	private String text;

	@JsonAlias({"place_name"})
	private String placeName;
	
	private List<Double> center = new ArrayList<>();
}
