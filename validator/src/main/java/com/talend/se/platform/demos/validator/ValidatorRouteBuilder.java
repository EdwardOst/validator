package com.talend.se.platform.demos.validator;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import com.talend.se.platform.demos.datavalidator.SimpleValidatorService;

public class ValidatorRouteBuilder extends RouteBuilder {

	SimpleValidatorService validator;	
	
	public ValidatorRouteBuilder(SimpleValidatorService validator) {
		this.validator = validator;
	}
	
	
	public class Greeter {
		public String greet(String body) {
			System.out.println("hello " + body);
			return "hello " + body;
		}
	}
	
	private Greeter greeter = new Greeter();
	
	@Override
	public void configure() throws Exception {

		if (validator == null)
			System.out.println("ValidatorRouteBuilder: null validator");
		else
			System.out.println("ValidatorRouteBuilder: validator exists");
		
		from("direct:validator")
		.setHeader("validator.temp", simple("${body}"))
		.bean(validator, "validate")
		.setHeader("validated", simple("${body}"))
		.setBody(simple("validator.temp"))
		.removeHeader("validtor.temp")
		.log("header: validated = ${header.validated}");
	}

}
