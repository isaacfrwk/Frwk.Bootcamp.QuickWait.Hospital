package com.quickwait.hospital.api.v1.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quickwait.hospital.domain.model.Address;
import com.quickwait.hospital.domain.service.AddressService;

@RestController
@RequestMapping("/v1/address")
public class AddressController {

	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = Objects.requireNonNull(addressService);
	}

	@GetMapping
	public List<Address> getAddresses(@RequestParam String addressName) {
		return addressService.getAddresses(addressName);
	}
}
