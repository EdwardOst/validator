package com.talend.se.platform.demos.datavalidator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleValidatorService {

	@Getter private final String name;
	@Getter private final Map<String, String> rules;
	@Getter private final StandardEvaluationContext evalContext;

	@Getter private Expression expr = null;
	
	public SimpleValidatorService() {
		this("anonymous", new LinkedHashMap<String, String>(), new StandardEvaluationContext());
	}

	public SimpleValidatorService(Map<String, String> rules) {
		this("anonymous", rules, new StandardEvaluationContext());
	}
	
	public SimpleValidatorService(String name, Map<String, String> rules) {
		this(name, rules, new StandardEvaluationContext());
	}
	
	public void parse() {
		variables();
		functions();
		SpelParserConfiguration parserConfig = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, this.getClass().getClassLoader());
		ExpressionParser parser = new SpelExpressionParser(parserConfig);
		expr = parser.parseExpression(buildExpression());
	}
	
	private String buildExpression() {
		StringJoiner sj = new StringJoiner(",", "{", "}");
		for (String exprName : rules.keySet()) {
			sj.add( exprName + ":" + rules.get(exprName));
		}
		return sj.toString();		
	}
	
    /**
	 * Register some Java reflect methods as functions that can be called from an expression.
	 * @param testContext the test evaluation context
	 */
	protected void functions() {
		try {
			evalContext.registerFunction("reverseString", DataValidatorFunctions.class.getDeclaredMethod("reverseString", String.class));
			evalContext.registerFunction("isEven", DataValidatorFunctions.class.getDeclaredMethod("isEven", int.class));
			evalContext.registerFunction("reverseInt", DataValidatorFunctions.class.getDeclaredMethod("reverseInt", int.class, int.class, int.class));
			evalContext.registerFunction("varargsFunctionReverseStringsAndMerge", DataValidatorFunctions.class.getDeclaredMethod("varargsFunctionReverseStringsAndMerge", String[].class));
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}

	/**
	 * Register some variables that can be referenced from the tests
	 * @param testContext the test evaluation context
	 */
	protected void variables() {
		evalContext.setVariable("max_age", 30);
	}
	
	public <T> T evaluate(Object rootObject, Class<T> resultType) {
		return expr.getValue(evalContext, rootObject, resultType);
	}

	public Map<String, Object> validate(Object rootObject) {
		return expr.getValue(evalContext, rootObject, Map.class);
	}
}
