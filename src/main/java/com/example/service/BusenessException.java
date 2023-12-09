package com.example.service;

import java.util.NoSuchElementException;

public class BusenessException extends NoSuchElementException {
	String error;
	String errorDescrption;

	public BusenessException(String error, String errorDescrption) {
		super(errorDescrption);
		this.error = error;
		this.errorDescrption = errorDescrption;
	}

	public BusenessException(String error) {
		super(error);
	}

}
