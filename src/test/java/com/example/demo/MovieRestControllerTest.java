package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.example.controllers.MovieRestController;
import com.example.model.Movie;
import com.example.service.MovieServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MovieRestController.class)
@AutoConfigureMockMvc(addFilters = false) // disables all the filters then you can pass the parameter
public class MovieRestControllerTest {

	@MockBean
	MovieServiceImpl movieImpl;

	@Mock
	Logger logger;

	@Autowired
	private MockMvc mockMvc;

	private static ObjectMapper mapper = new ObjectMapper();

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void saveMovie() throws Exception {
		Movie movie = new Movie(1, "dilip", "hindi", "Action", 4, "dilip.png");
		Mockito.when(logger.isInfoEnabled()).thenReturn(false);
		Mockito.when(movieImpl.insertMovie(movie)).thenReturn(movie); // mocking the insertMovie(), to use in controller

		MvcResult requestResult = mockMvc.perform(post("/insertMovie").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(getJsonObject(movie)).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse result = requestResult.getResponse();
		System.out.println("print the status return by post method: " + result.getStatus());
		assertEquals(201, result.getStatus());
	}

	@Test
	void getMovieById() throws Exception {
		Movie movie = new Movie(1, "dilip", "hindi", "Action", 4, "dilip.png");
		Mockito.when(movieImpl.getMovieById("1")).thenReturn(movie); // mocking the insertMovie(), to use in controller

		MvcResult requestResult = mockMvc.perform(get("/getMovie/12").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andReturn();

		MockHttpServletResponse result = requestResult.getResponse();
		System.out.println("print the status return by get method: " + result.getStatus());
		// System.out.println("print the modles return by get method: " +
		// requestResult.getFlashMap());
		assertEquals(200, result.getStatus());
		// second way to validate
		MockHttpServletResponse mockHttpServletResponse= mockMvc.perform(get("/getMovie/1").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
	}

	public String getJsonObject(Movie movie) {
		String json = null;
		try {
			json = mapper.writeValueAsString(movie);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;

	}
}
