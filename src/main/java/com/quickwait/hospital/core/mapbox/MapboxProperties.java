package com.quickwait.hospital.core.mapbox;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("mapbox")
public class MapboxProperties {

	private String host;
	
	private String token;
	
	private String hospitalsLimit;
}
