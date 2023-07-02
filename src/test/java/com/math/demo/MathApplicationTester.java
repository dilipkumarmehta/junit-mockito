package com.math.demo;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// @RunWith attaches a runner with the test class to initialize the test data
@ExtendWith(MockitoExtension.class)
public class MathApplicationTester {

	// @InjectMocks annotation is used to create and inject the mock object

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	CalculatorService calcService;

	@InjectMocks
	MathApplication mathApplication ;//= new MathApplication();

	@Test
   public void testAdd(){
      //add the behavior of calc service to add two numbers
      when(calcService.add(10.0,20.0)).thenReturn(30.00);
      //test the add functionality
      Assertions.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
   }
}