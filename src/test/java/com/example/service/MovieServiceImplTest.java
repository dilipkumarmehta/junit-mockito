/**
 * 
 */
package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Movie;
import com.example.repository.MovieRepository;

/**
 * 
 */
@SpringBootTest
class MovieServiceImplTest {

	Movie movie = null;
	@InjectMocks // inject the dependency of the class which is required in movieServiceImpl
					// class
	MovieServiceImpl movieServiceImpl;

	@Mock
	MovieRepository mRepo; // this will mock the object to not call db

	@Test //test first 2 lines
	public void getMovieById() {
		when(mRepo.findById(1)).thenReturn(getMovieStub());
		assertEquals(movieServiceImpl.getMovieById("1"),getMovieStub().get());
	}

	@Test //this for Business exception 
	public void getMovieByIdWithException() {
		when(mRepo.findById(1)).thenReturn(getMovieStubExcep());
		BusenessException assertThrows2 = assertThrows(BusenessException.class, ()->movieServiceImpl.getMovieById("1"));
		assertEquals(assertThrows2.getMessage(), "Not allowed to access this movie");
	
	}
	
	@Test  //this is for catch block
	public void getMovieByIdNoSuchElementException() {
		when(mRepo.findById(1)).thenThrow(new NoSuchElementException());
		NoSuchElementException assertThrows2 = assertThrows(NoSuchElementException.class, ()->movieServiceImpl.getMovieById("1"));
		assertEquals(assertThrows2.getMessage(),null); //because we have not passed any message to nusuchelementException
	
	}
	public Optional<Movie> getMovieStub() {
		if (movie == null) {
			movie = new Movie();
			movie.setId(1);
			movie.setImageUrl("url");
			movie.setLanguage("hindi");
			movie.setName("gadara");
			movie.setRate(2);
			movie.setType("family");
			return Optional.of(movie);
		}
		return Optional.of(movie);
	}
	public Optional<Movie> getMovieStubExcep() {
		if (movie == null) {
			movie = new Movie();
			movie.setId(1);
			movie.setImageUrl("url");
			movie.setLanguage("hindi");
			movie.setName("gadar");
			movie.setRate(2);
			movie.setType("family");
			return Optional.of(movie);
		}
		return Optional.of(movie);
	}


}
