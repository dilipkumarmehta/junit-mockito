package com.student.demo;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.student.demo.Student;
import com.student.demo.StudentService;

public class StudentTest {
	StudentService studentService = mock(StudentService.class);
	Student student = new Student(studentService);
	//Student student= Mock(Student.class);

@Test
 public void testaverageMarks()
 {
  Mockito.when(studentService.getToalmarks()).thenReturn(30);
  Mockito.when(studentService.getTotalStudent()).thenReturn(10);
  Assertions.assertEquals(student.getAveragemarks(), 3);
		
 }


}