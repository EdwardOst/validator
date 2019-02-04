package com.talend.se.platform.demos.validator;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class SampleData {

	private List<Customer> data = new ArrayList<Customer>();
	
	public List<Customer> getData() {
		return data;
	}

	{
		data.add(new Customer("Ost", "Edward", 50, new LocalDate(1967, 6, 10), new Address("12908 Brunswick Ln", null, "Bowie", "MD", "20715", "USA")));
		data.add(new Customer("Antoine", "Christophe", 40, new LocalDate(1977, 7, 11), new Address("100 Times Square", null, "NY", "NY", "12345", "USA")));
		data.add(new Customer("Zubair", "Usman", 30, new LocalDate(1987, 8, 12), new Address("200 Main St", null, "Hoboken", "NJ", "23456", "USA")));
	}
	
}
