package com.talend.se.platform.demos.validator;

import java.util.Map;
import java.util.TreeMap;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.talend.se.platform.demos.datavalidator.SimpleValidatorService;

public class Main {
	
	public static void main(String args[]) {
		CamelContext context = new DefaultCamelContext();
		String dir = "/Users/eost/Documents/se/demo/camel/in";
		String file = "test.txt";
		SimpleValidatorService validator = new SimpleValidatorService("testCamelDefaultRules", defaultRules(), new StandardEvaluationContext());
		
		try {
			context.addRoutes(new ValidatorRouteBuilder(validator));
			context.start();
			Thread.sleep(10000);
			context.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
