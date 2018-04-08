package com.daloji.tracker.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.daloji.tracker.model.Credential;
import com.daloji.tracker.model.Localisation;
import com.daloji.tracker.model.Status;
import com.daloji.tracker.model.StatusController;
import com.daloji.tracker.model.Tracker;
import com.daloji.tracker.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackerControllerTest {

	MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void findTrackerByName() throws Exception {

		Tracker tracker = new Tracker();
		tracker.setInfo("info");
		tracker.setIdentification("identification");
		tracker.setInfo("info");
		tracker.setName("name");
		Credential credential = new Credential();
		credential.setNumber("000001");
		credential.setNbAllSmsSend(5);
		credential.setNbSMS(12);
		credential.setNbSmsReceive(14);
		credential.setOperator("operator");
		credential.setStatus(Status.ACTIVED);
		credential.setNbSmsSend(37);
		List<Credential> set = new ArrayList<Credential>();
		set.add(credential);
		tracker.setCredential(set);
		String json = Utils.objectToJson(tracker);
		MvcResult result =mvc.perform(post("/api/tracker/addtracker").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isCreated()).andReturn();
		String content=result.getResponse().getContentAsString();
		StatusController status = (StatusController) Utils.jsonToObject(content, StatusController.class);
		assertEquals(status.getCode(),201);
		assertEquals(status.getType(),"/tracker/addtracker/");
		String jsonTrack = status.getMessage();
		Tracker addtracker = (Tracker) Utils.jsonToObject(jsonTrack, Tracker.class);

		assertEquals(addtracker.getInfo(),tracker.getInfo());
		assertEquals(addtracker.getIdentification(),tracker.getIdentification());
		assertEquals(addtracker.getName(),tracker.getName());
		assertEquals(addtracker.getCredential().size(),tracker.getCredential().size());
		assertEquals(addtracker.getCredential().get(0).getStatus(),tracker.getCredential().get(0).getStatus());
		assertEquals(addtracker.getCredential().get(0).getDateEndCredit(),tracker.getCredential().get(0).getDateEndCredit());
		assertEquals(addtracker.getCredential().get(0).getNbAllSmsSend(),tracker.getCredential().get(0).getNbAllSmsSend());
		assertEquals(addtracker.getCredential().get(0).getNbSMS(),tracker.getCredential().get(0).getNbSMS());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());
		assertEquals(addtracker.getCredential().get(0).getNumber(),tracker.getCredential().get(0).getNumber());
		assertEquals(addtracker.getCredential().get(0).getOperator(),tracker.getCredential().get(0).getOperator());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());

		result = this.mvc.perform(get(
				"/api/tracker/name"))
				.andExpect(status().isOk()).andReturn();

		content=result.getResponse().getContentAsString();

		addtracker = (Tracker) Utils.jsonToObject(jsonTrack, Tracker.class);

		assertEquals(addtracker.getInfo(),tracker.getInfo());
		assertEquals(addtracker.getIdentification(),tracker.getIdentification());
		assertEquals(addtracker.getName(),tracker.getName());
		assertEquals(addtracker.getCredential().size(),tracker.getCredential().size());
		assertEquals(addtracker.getCredential().get(0).getStatus(),tracker.getCredential().get(0).getStatus());
		assertEquals(addtracker.getCredential().get(0).getDateEndCredit(),tracker.getCredential().get(0).getDateEndCredit());
		assertEquals(addtracker.getCredential().get(0).getNbAllSmsSend(),tracker.getCredential().get(0).getNbAllSmsSend());
		assertEquals(addtracker.getCredential().get(0).getNbSMS(),tracker.getCredential().get(0).getNbSMS());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());
		assertEquals(addtracker.getCredential().get(0).getNumber(),tracker.getCredential().get(0).getNumber());
		assertEquals(addtracker.getCredential().get(0).getOperator(),tracker.getCredential().get(0).getOperator());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());

		result =mvc.perform(get(
				"/api/tracker/delete/name"))
				.andExpect(status().isOk()).andReturn();	
	}


	@Test
	public void createTracker() throws Exception {
		Tracker tracker = new Tracker();
		tracker.setInfo("info");
		tracker.setIdentification("identification");
		tracker.setInfo("info");
		tracker.setName("name");
		Credential credential = new Credential();
		credential.setNumber("000001");
		credential.setNbAllSmsSend(5);
		credential.setNbSMS(12);
		credential.setNbSmsReceive(14);
		credential.setOperator("operator");
		credential.setStatus(Status.ACTIVED);
		credential.setNbSmsSend(37);
		List<Credential> set = new ArrayList<Credential>();
		set.add(credential);
		tracker.setCredential(set);
		String json = Utils.objectToJson(tracker);
		MvcResult result =mvc.perform(post("/api/tracker/addtracker").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isCreated()).andReturn();
		String content=result.getResponse().getContentAsString();
		StatusController status = (StatusController) Utils.jsonToObject(content, StatusController.class);
		assertEquals(status.getCode(),201);
		assertEquals(status.getType(),"/tracker/addtracker/");
		String jsonTrack = status.getMessage();
		Tracker addtracker = (Tracker) Utils.jsonToObject(jsonTrack, Tracker.class);

		assertEquals(addtracker.getInfo(),tracker.getInfo());
		assertEquals(addtracker.getIdentification(),tracker.getIdentification());
		assertEquals(addtracker.getName(),tracker.getName());
		assertEquals(addtracker.getCredential().size(),tracker.getCredential().size());
		assertEquals(addtracker.getCredential().get(0).getStatus(),tracker.getCredential().get(0).getStatus());
		assertEquals(addtracker.getCredential().get(0).getDateEndCredit(),tracker.getCredential().get(0).getDateEndCredit());
		assertEquals(addtracker.getCredential().get(0).getNbAllSmsSend(),tracker.getCredential().get(0).getNbAllSmsSend());
		assertEquals(addtracker.getCredential().get(0).getNbSMS(),tracker.getCredential().get(0).getNbSMS());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());
		assertEquals(addtracker.getCredential().get(0).getNumber(),tracker.getCredential().get(0).getNumber());
		assertEquals(addtracker.getCredential().get(0).getOperator(),tracker.getCredential().get(0).getOperator());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());

		result =mvc.perform(get(
				"/api/tracker/delete/name"))
				.andExpect(status().isOk()).andReturn();	

		content=result.getResponse().getContentAsString();
		status = (StatusController) Utils.jsonToObject(content, StatusController.class);


		assertEquals(status.getCode(),200);
		assertEquals(status.getType(),"/tracker/delete/name");
		jsonTrack = status.getMessage();


		Tracker deletetracker = (Tracker) Utils.jsonToObject(jsonTrack, Tracker.class);
		assertEquals(deletetracker.getInfo(),tracker.getInfo());
		assertEquals(deletetracker.getIdentification(),tracker.getIdentification());
		assertEquals(deletetracker.getName(),tracker.getName());
		assertEquals(deletetracker.getCredential().size(),tracker.getCredential().size());
		assertEquals(deletetracker.getCredential().get(0).getStatus(),tracker.getCredential().get(0).getStatus());
		assertEquals(deletetracker.getCredential().get(0).getDateEndCredit(),tracker.getCredential().get(0).getDateEndCredit());
		assertEquals(deletetracker.getCredential().get(0).getNbAllSmsSend(),tracker.getCredential().get(0).getNbAllSmsSend());
		assertEquals(deletetracker.getCredential().get(0).getNbSMS(),tracker.getCredential().get(0).getNbSMS());
		assertEquals(deletetracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());
		assertEquals(deletetracker.getCredential().get(0).getNumber(),tracker.getCredential().get(0).getNumber());
		assertEquals(deletetracker.getCredential().get(0).getOperator(),tracker.getCredential().get(0).getOperator());
		assertEquals(deletetracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());


	}

	@Test
	public void createLocalisation() throws Exception {

		Tracker tracker = new Tracker();
		tracker.setInfo("info");
		tracker.setIdentification("identification");
		tracker.setInfo("info");
		tracker.setName("name");
		Credential credential = new Credential();
		credential.setNumber("+330543445");
		credential.setNbAllSmsSend(5);
		credential.setNbSMS(12);
		credential.setNbSmsReceive(14);
		credential.setOperator("operator");
		credential.setStatus(Status.ACTIVED);
		credential.setNbSmsSend(37);
		List<Credential> set = new ArrayList<Credential>();
		set.add(credential);
		tracker.setCredential(set);
		String json = Utils.objectToJson(tracker);
		MvcResult result =mvc.perform(post("/api/tracker/addtracker").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isCreated()).andReturn();
		String content=result.getResponse().getContentAsString();
		StatusController status = (StatusController) Utils.jsonToObject(content, StatusController.class);
		assertEquals(status.getCode(),201);
		assertEquals(status.getType(),"/tracker/addtracker/");
		String jsonTrack = status.getMessage();
		Tracker addtracker = (Tracker) Utils.jsonToObject(jsonTrack, Tracker.class);

		assertEquals(addtracker.getInfo(),tracker.getInfo());
		assertEquals(addtracker.getIdentification(),tracker.getIdentification());
		assertEquals(addtracker.getName(),tracker.getName());
		assertEquals(addtracker.getCredential().size(),tracker.getCredential().size());
		assertEquals(addtracker.getCredential().get(0).getStatus(),tracker.getCredential().get(0).getStatus());
		assertEquals(addtracker.getCredential().get(0).getDateEndCredit(),tracker.getCredential().get(0).getDateEndCredit());
		assertEquals(addtracker.getCredential().get(0).getNbAllSmsSend(),tracker.getCredential().get(0).getNbAllSmsSend());
		assertEquals(addtracker.getCredential().get(0).getNbSMS(),tracker.getCredential().get(0).getNbSMS());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());
		assertEquals(addtracker.getCredential().get(0).getNumber(),tracker.getCredential().get(0).getNumber());
		assertEquals(addtracker.getCredential().get(0).getOperator(),tracker.getCredential().get(0).getOperator());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());



		Localisation localisation = new Localisation();

		localisation.setSpeed(344);
		localisation.setLatitude(43.54);;
		localisation.setLongitude(34.45);;
		localisation.setNumber("+330543445");;

		json = Utils.objectToJson(localisation);

		result =mvc.perform(post("/api/tracker/addlocalisation").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isCreated()).andReturn();




		content=result.getResponse().getContentAsString();
		status = (StatusController) Utils.jsonToObject(content, StatusController.class);
		assertEquals(status.getCode(),201);
		assertEquals(status.getType(),"/tracker/addlocalisation/");
		jsonTrack = status.getMessage();
		Localisation addlocalisation = (Localisation) Utils.jsonToObject(jsonTrack, Localisation.class);
		assertEquals(addlocalisation.getDateloc(),localisation.getDateloc());
		assertEquals(addlocalisation.getLatitude(),localisation.getLatitude(),1);
		assertEquals(addlocalisation.getLongitude(),localisation.getLongitude(),1);
		assertEquals(addlocalisation.getNumber(),localisation.getNumber());
		assertEquals(addlocalisation.getTracker(),localisation.getTracker());
		
		
		result = this.mvc.perform(get(
				"/api/tracker/name"))
				.andExpect(status().isOk()).andReturn();

		content=result.getResponse().getContentAsString();

		addtracker = (Tracker) Utils.jsonToObject(content, Tracker.class);

		assertEquals(addtracker.getInfo(),tracker.getInfo());
		assertEquals(addtracker.getIdentification(),tracker.getIdentification());
		assertEquals(addtracker.getName(),tracker.getName());
		assertEquals(addtracker.getCredential().size(),tracker.getCredential().size());
		assertEquals(addtracker.getCredential().get(0).getStatus(),tracker.getCredential().get(0).getStatus());
		assertEquals(addtracker.getCredential().get(0).getDateEndCredit(),tracker.getCredential().get(0).getDateEndCredit());
		assertEquals(addtracker.getCredential().get(0).getNbAllSmsSend(),tracker.getCredential().get(0).getNbAllSmsSend());
		assertEquals(addtracker.getCredential().get(0).getNbSMS(),tracker.getCredential().get(0).getNbSMS());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());
		assertEquals(addtracker.getCredential().get(0).getNumber(),tracker.getCredential().get(0).getNumber());
		assertEquals(addtracker.getCredential().get(0).getOperator(),tracker.getCredential().get(0).getOperator());
		assertEquals(addtracker.getCredential().get(0).getNbSmsReceive(),tracker.getCredential().get(0).getNbSmsReceive());
		
		assertEquals(addtracker.getLocalisation().get(0).getLatitude(),localisation.getLatitude(),1);
		assertEquals(addtracker.getLocalisation().get(0).getLongitude(),localisation.getLongitude(),1);
		assertEquals(addtracker.getLocalisation().get(0).getNumber(),localisation.getNumber());
		assertEquals(addtracker.getLocalisation().get(0).getSpeed(),localisation.getSpeed());
		assertEquals(addtracker.getLocalisation().get(0).getDateloc(),localisation.getDateloc());
		
		result =mvc.perform(get(
				"/api/tracker/delete/name"))
				.andExpect(status().isOk()).andReturn();	
	}
	@Test
	public void createCredential() throws Exception {

		/*
		mvc.perform(post("/api/tracker/addtracker").accept(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"nom tracker\", \"identification\":\"identification tracker\"}").contentType(MediaType.APPLICATION_JSON)).andExpect(
						status().isCreated()).andExpect(jsonPath("identification", equalTo("identification tracker"))).andExpect(jsonPath("name", equalTo("nom tracker"))).andExpect(jsonPath("id", equalTo(1)));

		mvc.perform(post("/api/tracker/addcredential").accept(MediaType.APPLICATION_JSON).content("{\"number\":\"+3363445545\",\"operator\":\"test\", \"status\":\"ACTIVED\"}").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isCreated()).andExpect(jsonPath("number", equalTo("+3363445545"))).andExpect(jsonPath("operator", equalTo("test"))).andExpect(jsonPath("status", equalTo("ACTIVED")));
		 */

		//mvc.perform(post("/api/tracker/addlocalisation").accept(MediaType.APPLICATION_JSON).content("{\"number\":\"+3363445545\",\"longitude\":34.2, \"latitude\":34.23}").contentType(MediaType.APPLICATION_JSON)).andExpect(
		//			status().isCreated()).andExpect(jsonPath("identification", equalTo("identification tracker"))).andExpect(jsonPath("name", equalTo("nom tracker")));


	}

}
