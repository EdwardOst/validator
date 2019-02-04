package com.talend.se.platform.demos.datavalidator;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.joda.time.LocalDate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SimpleValidatorServiceTest {

	SimpleValidatorService validator;
	SampleData data;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		validator = new SimpleValidatorService("testDefaultRules", defaultRules(), new StandardEvaluationContext());
		data = new SampleData();
		validator.parse();
	}

	private static Map<String, String>  defaultRules() {
		Map<String, String> expressions = new TreeMap<>();
		expressions.put("fullname", "lastname + ', ' + firstname");
		expressions.put("age", "age");
		expressions.put("date_of_birth", "dob");
		expressions.put("address", "address.street1 + ', ' + address.city");
		expressions.put("youth_demographic", "dob.isAfter(new org.joda.time.LocalDate(1970, 1, 1))");
		expressions.put("carrousel", "age > #max_age");
		return expressions;
	}
	
	private static void printResult(Map<String, Object> result) {
		for (Entry<String, Object> entry : result.entrySet()) {
			String valueType = "value.class=" + entry.getValue().getClass().getName();
			System.out.printf("%s[%s] = %s\n", entry.getKey(), valueType, "'" + entry.getValue().toString()  + "'");
		}
		System.out.printf("----------------\n");
	}
	
	@Test
	public void test() {
		for (Customer customer : data.getData()) {
			System.out.println("Processing " + customer);
			Map<String, Object> result = validator.evaluate(customer, Map.class);
			printResult(result);
			assertEquals(customer.getDob(), (LocalDate) result.get("date_of_birth"));
			assertEquals(customer.getLastname() + ", " + customer.getFirstname(), result.get("fullname"));
			assertEquals(customer.getAge(), result.get("age"));
			assertEquals(customer.getAge() > 30, result.get("carrousel"));
			assertEquals(customer.getAddress().getStreet1() + ", " + customer.getAddress().getCity(), result.get("address"));
			assertEquals(customer.getDob().isAfter(new LocalDate(1970, 1, 1)), result.get("youth_demographic"));
		}
	}

}