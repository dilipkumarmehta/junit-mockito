package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import com.example.model.Movie;
import com.example.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

	@Autowired
	MovieRepository mRepo;

	@Override
	public String welcomeMsg() {
		LOGGER.info("inside welcomeMsg --");
		return "Welcome to spring boot services";
	}

	@Override
	public List<String[]> getMovies() {
		LOGGER.info("inside getMovies service --");
		List<Movie> movies = mRepo.findAll();
		List<String[]> obj = new ArrayList<>();

		for (Movie m : movies) {
			String[] objlist = new String[6];
			objlist[0] = Integer.toString(m.getId());
			objlist[1] = m.getName();
			objlist[2] = m.getLanguage();
			objlist[3] = m.getType();
			objlist[4] = Double.toString(m.getRate());
			objlist[5] = m.getImageUrl();
			obj.add(objlist);
		}
		return obj;
	}

	@Override
	public Movie getMovieById(String id) {
		LOGGER.info("inside getMovieById service --");
		try {
			Optional<Movie> movie = mRepo.findById(Integer.parseInt(id));
			if (movie.get().getName().equals("gadar")) {
				BusenessException be = new BusenessException("400", "Not allowed to access this movie");
				be.initCause(new PermissionDeniedDataAccessException("don't acces moview with tis id", null));
				throw be;
			}
			return movie.get();
		} catch (NoSuchElementException e) {
			throw e;
		}
	}

	@Override
	public Movie insertMovie(Movie movie) {
		LOGGER.info("inside insertMovie service --");
		Movie movieObj = mRepo.save(movie);
		return movieObj;
	}

}
