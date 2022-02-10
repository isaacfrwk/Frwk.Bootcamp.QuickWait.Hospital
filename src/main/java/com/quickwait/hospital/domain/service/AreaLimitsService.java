package com.quickwait.hospital.domain.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AreaLimitsService {

	public static final long MIN_VALID_VALUE_LONGITUDE = -180;
	public static final long MAX_VALID_VALUE_LONGITUDE = 180;
	public static final long MIN_VALID_VALUE_LATITUDE = -90;
	public static final long MAX_VALID_VALUE_LATITUDE = 90;
	public static final long MIN_VALID_VALUE_RADIX = 0;
	
	public static final String MIN_LONGITUDE = "minLongitude";
	public static final String MIN_LATITUDE = "minLatitude";
	public static final String MAX_LONGITUDE = "maxLongitude";
	public static final String MAX_LATITUDE = "maxLatitude";
	
	private static final Double ONE_DEGREE_OF_LATITUDE_IN_KM = 110.574;
	
	public Map<String, Double> calcLatitudeAndLongitudeLimits(double longitude, double latitude, double radix) {
		Map<String, Double> limits = new HashMap<String, Double>();
		
		limits.put(MIN_LONGITUDE, getMinLongitude(longitude, latitude, radix));
		limits.put(MIN_LATITUDE, getMinLatitude(latitude, radix));
		limits.put(MAX_LONGITUDE, getMaxLongitude(longitude, latitude, radix));
		limits.put(MAX_LATITUDE, getMaxLatitude(latitude, radix));
		
		return limits;
	}
	
	private double getMinLongitude(double longitude, double latitude, double radix) {
		double oneDegreeOfLongitudeInKm = getOneDegreeOfLongitudeInKm(latitude);
		if(oneDegreeOfLongitudeInKm == 0) return longitude;
		
		double minLongitude = longitude - (radix / oneDegreeOfLongitudeInKm);
		
		if(minLongitude < Double.valueOf(MIN_VALID_VALUE_LONGITUDE)) {
			minLongitude = MIN_VALID_VALUE_LONGITUDE;
		}
		
		return minLongitude;
	}
	
	private double getMaxLongitude(double longitude, double latitude, double radix) {
		double oneDegreeOfLongitudeInKm = getOneDegreeOfLongitudeInKm(latitude);
		if(oneDegreeOfLongitudeInKm == 0) return longitude;
		
		double maxLongitude = longitude + (radix / oneDegreeOfLongitudeInKm);
		
		if(maxLongitude > Double.valueOf(MAX_VALID_VALUE_LONGITUDE)) {
			maxLongitude = MAX_VALID_VALUE_LONGITUDE;
		}
		
		return maxLongitude;
	}
	
	private double getMinLatitude(double latitude, double radix) {
		double minLatitude = latitude - (radix / ONE_DEGREE_OF_LATITUDE_IN_KM);
		
		if(minLatitude < Double.valueOf(MIN_VALID_VALUE_LATITUDE)) {
			minLatitude = MIN_VALID_VALUE_LATITUDE;
		}
		
		return minLatitude;
	}
	
	private double getMaxLatitude(double latitude, double radix) {
		double maxLatitude = latitude + (radix / ONE_DEGREE_OF_LATITUDE_IN_KM);
		
		if(maxLatitude > Double.valueOf(MAX_VALID_VALUE_LATITUDE)) {
			maxLatitude = MAX_VALID_VALUE_LATITUDE;
		}
		
		return maxLatitude;
	}
	
	private double getOneDegreeOfLongitudeInKm(double latitude) {
		return 111.320 * Math.cos(latitude);
	}
}
