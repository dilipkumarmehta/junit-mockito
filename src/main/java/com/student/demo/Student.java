package com.student.demo;

public class Student {

	StudentService studentService;

  public Student(StudentService studentService) {
		this.studentService = studentService;
	}
	public int getAveragemarks() {
		System.out.println(""+studentService.getToalmarks());
		return studentService.getToalmarks() / studentService.getTotalStudent();
	}
}
