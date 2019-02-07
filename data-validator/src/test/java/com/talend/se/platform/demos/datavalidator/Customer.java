package com.talend.se.platform.demos.datavalidator;

import lombok.Data;
import org.joda.time.LocalDate;

@Data
public class Customer {
	
	private final String lastname;
	private final String firstname;
	private final int age;
	private final LocalDate dob;
	private final Address address;

}
