package com.daloji.tracker.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TrackerControllerTest {

	//@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	//@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	//@Test
	public void findTrackerByName() throws Exception {
		
		this.mvc.perform(get(
				"/api/tracker/Tracker"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", equalTo("Tracker")));
	}
	
	
	//@Test
	public void createTracker() throws Exception {

		mvc.perform(post("/api/tracker/add").content(
				"{\"name\": \"tracker\"\"}")).andExpect(
						status().isCreated()).andExpect(
								header().string("Location", containsString("people/")));
	}
}
