package com.math.demo;

public class MathApplication {
	private CalculatorService calcService;

	/*
	 * MathApplication(CalculatorService calcService) { this.calcService =
	 * calcService; }
	 */
	public double add(double input1, double input2) {
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
}