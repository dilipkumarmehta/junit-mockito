package com.dilip.mock;

import org.springframework.beans.factory.annotation.Autowired;

public class MathApplication {

	@Autowired
	private CalculatorService calcService;

	public double add(double input1, double input2) {
		double sum = addNumber(input1);
		if (sum == 100) {
			System.out.println("hello world");
		}else{
			System.out.println("hello");
		}
		verify();
		return calcService.add(input1, input2);
	}

	public double subtract(double input1, double input2) {
		return calcService.subtract(input1, input2);
	}

	public double multiply(double input1, double input2) {
		return calcService.multiply(input1, input2);
	}

	public double divide(double input1, double input2) {
		return calcService.divide(input1, input2);
	}

	public double addNumber(double number) {
		if (number > 10) {
			System.out.println("Number is less than 10");

		} else {
			System.out.println("Number is greater than 10");
		}
		return number * 10;
	}

	public void verify() {
		System.out.println("verify");
	}

}