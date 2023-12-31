package com.example.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Movie;
import com.example.service.MovieService;


@CrossOrigin(maxAge = 3600)
@RestController
public class MovieRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieRestController.class);

	@Autowired
	MovieService mService;

	
	@CrossOrigin(origins = "*")
	@PostMapping("/insertMovie")
	public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
		LOGGER.info("***saveMovie action called..");
		return new ResponseEntity<Movie>(mService.insertMovie(movie),HttpStatus.CREATED);

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/getMovie/{id}")
	public Movie getMovieById(@PathVariable String id) {
		return mService.getMovieById(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/getMovies", produces = "application/json")
	public List<String[]> getMovies() {
		LOGGER.info("**********getMovies action called..");
		return mService.getMovies();

	}

	@GetMapping("/welcome")
	public String getWelcomeMsg() {
		LOGGER.info("getWelcomeMsg action called..");
		return mService.welcomeMsg();

	}

}
