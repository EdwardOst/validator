package com.talend.se.platform.demos.validator;

import lombok.Data;

@Data
public class Address {

	private final String street1;
	private final String street2;
	private final String city;
	private final String state;
	private final String zip;
	private final String country;
}
