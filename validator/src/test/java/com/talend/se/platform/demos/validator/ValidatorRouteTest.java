package com.talend.se.platform.demos.validator;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.talend.se.platform.demos.datavalidator.SimpleValidatorService;


import com.talend.se.platform.demos.validator.SampleData;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class ValidatorRouteTest extends CamelTestSupport {

	private Map<String, String>  defaultRules() {
		Map<String, String> expressions = new TreeMap<>();
		expressions.put("fullname", "lastname + ', ' + firstname");
		expressions.put("age", "age");
		expressions.put("date_of_birth", "dob");
		expressions.put("address", "address.street1 + ', ' + address.city");
		expressions.put("youth_demographic", "dob.isAfter(new org.joda.time.LocalDate(1970, 1, 1))");
		expressions.put("carrousel", "age > #max_age");
		return expressions;
	}

	SimpleValidatorService validator = new SimpleValidatorService("testCamelDefaultRules", defaultRules(), new StandardEvaluationContext());;
	SampleData data = new SampleData();
	
	@Override
    public void setUp() throws Exception {
		validator.parse();
		super.setUp();	
	}

	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new ValidatorRouteBuilder(validator);
	}
	
	@Test
	public void testValidator() {
		for (Customer customer : data.getData()) {
			template.sendBody("direct:validator", customer);
		}
//		fail("Not yet implemented");
	}

}
