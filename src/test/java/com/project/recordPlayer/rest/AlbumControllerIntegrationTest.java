package com.project.recordPlayer.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.recordPlayer.domain.Album;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:album-schema.sql",
"classpath:album-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AlbumControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void createTest() throws Exception {
		// Create test data and an expected piece of data
		Album newAlbum = new Album("Hotspot and the Bimbles", "EARWIGS", "askjeeves", "googly", 2000);
		Album savedAlbum = new Album(2L, "Hotspot and the Bimbles", "EARWIGS", "askjeeves", "googly", 2000);	
		
		// Turn both into JSON
		String newAlbumAsJSON = this.mapper.writeValueAsString(newAlbum);
		String savedAlbumAsJSON = this.mapper.writeValueAsString(savedAlbum);
		
		// Post the test data to mock DB
		RequestBuilder mockRequest = post("/album")
				.contentType(MediaType.APPLICATION_JSON)
				.content(newAlbumAsJSON);
		
		// Grab status code and output
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(savedAlbumAsJSON);
		
		// Run request and compare to expect values 
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void readTest() throws Exception {
		Album testAlbum = new Album(1L, "whenben", "catshark and the flavorcrumpets", "askjeeves", "googly", 2000);
		List<Album> allAlbum = List.of(testAlbum);
		String testAlbumAsJSON = this.mapper.writeValueAsString(allAlbum);
		RequestBuilder mockRequest = get("/albums");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testAlbumAsJSON);
		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void deleteTest() throws Exception {
		RequestBuilder mockRequest = delete("/album/1");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("false");
		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);	
	}
	
	@Test
	void updateTest() throws Exception {
		Album testAlbum = new Album("whenben", "catshark and the flavorcrumpets", "askjeeves", "googly", 2000);
		String testAlbumAsJSON = this.mapper.writeValueAsString(testAlbum);
		RequestBuilder mockRequest = put("/album/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(testAlbumAsJSON);
		Album savedAlbum = new Album(1L, "whenben", "catshark and the flavorcrumpets", "askjeeves", "googly", 2000);
		String savedAlbumAsJSON = this.mapper.writeValueAsString(savedAlbum);
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(savedAlbumAsJSON);
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);	
	}

}
