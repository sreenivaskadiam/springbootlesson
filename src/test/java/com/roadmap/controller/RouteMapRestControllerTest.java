package com.roadmap.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.roadmap.restservice.RouteMapRestService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RouteMapRestControllerTest {
	@MockBean
	private RouteMapRestService routeMapRestService;
	
	@Autowired
	private MockMvc mockMvc;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testRouteMap() throws Exception { 
		when(routeMapRestService.getRoadMap("boston", "new york")).thenReturn("yes");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>() ;
		params.add("origin", "boston");
		params.add("destination", "new york");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/connected").queryParams(params);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals("yes", result.getResponse().getContentAsString(), "Expected results");

	}
	
	@Test
	void testRouteMapWithNoRoute() throws Exception {
		when(routeMapRestService.getRoadMap("boston", "trenton")).thenReturn("no");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>() ;
		params.add("origin", "boston");
		params.add("destination", "trenton");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/connected").queryParams(params);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("results no route"+result.getResponse().getContentAsString());
		assertEquals("no", result.getResponse().getContentAsString(), "Expected results");

	}
	
	

}
