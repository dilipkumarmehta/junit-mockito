package com.student.demo;

public class Student {

	StudentService studentService;

  public Student(StudentService studentService) {
		this.studentService = studentService;
	}
	public int getAveragemarks() {
		return studentService.getToalmarks() / studentService.getTotalStudent();
	}
}
