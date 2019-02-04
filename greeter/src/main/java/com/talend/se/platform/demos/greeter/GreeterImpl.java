package com.talend.se.platform.demos.greeter;

import com.talend.se.platform.demos.greeter.api.Greeter;

public class GreeterImpl implements Greeter {

	public String hi() {
		return "hi";
	}

	public String greet(String name) {
		return "hello " + name;
	}

	public String bye() {
		return "bye";
	}

}
