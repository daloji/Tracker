package com.daloji.tracker.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.daloji.tracker.model.Localisation;
import com.daloji.tracker.model.Tracker;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackerRepositoryTest {


	@Autowired
	LocalisationRepository localisationRepository;
	
	@Autowired
	TrackerRepository repository;


	@Test
	public void addTracker() {

		Tracker tracker1 = new Tracker();
		tracker1.setIdentification("identification");
		tracker1.setInfo("info");
		tracker1.setName("nameTracker");
		List<String> listnumero = new ArrayList<String>();
		listnumero.add("00003");
		listnumero.add("00001");
		listnumero.add("00002");
		tracker1.setNumber(listnumero);
		Tracker mytracker = repository.save(tracker1);
		assertEquals(mytracker.getIdentification(),tracker1.getIdentification());
		assertEquals(mytracker.getInfo(),tracker1.getInfo());
		assertEquals(mytracker.getName(),tracker1.getName());
		assertEquals(mytracker.getNumber().size(),3);

		
		Tracker tracker = repository.findByName("nameTracker");
		assertNotEquals(tracker,null);
		assertEquals(tracker.getIdentification(),tracker1.getIdentification());
		assertEquals(tracker.getInfo(),tracker1.getInfo());
		assertEquals(tracker.getName(),tracker1.getName());
		assertEquals(mytracker.getLocalisation(),null);
		
		Tracker tracker2 = new Tracker();
		tracker2.setIdentification("identification1");
		tracker2.setInfo("info1");
		tracker2.setName("name1");

		mytracker = repository.save(tracker2);
		assertEquals(mytracker.getIdentification(),tracker2.getIdentification());
		assertEquals(mytracker.getInfo(),tracker2.getInfo());
		assertEquals(mytracker.getName(),tracker2.getName());
		assertEquals(mytracker.getLocalisation(),null);
		
		
		repository.delete(tracker1);
		repository.delete(tracker2);

	}
	@Test
	public void addLocalisationTracker() {
	
		Tracker tracker1 = new Tracker();
		tracker1.setIdentification("identification1");
		tracker1.setInfo("info");
		tracker1.setName("name1");
		List<String> listnumero = new ArrayList<String>();
		listnumero.add("00003");
		listnumero.add("00001");
		listnumero.add("00002");
		tracker1.setNumber(listnumero);
		
		Tracker mytracker = repository.save(tracker1);
		
		
		Localisation localisation = new Localisation();
		localisation.setLatitude(30.20);
		localisation.setLongitude(20.20);
		localisation.setNumber("location1");
		localisation.setTracker(mytracker);
		Set<Localisation> setloc = new HashSet<>();
		setloc.add(localisation);
		localisationRepository.save(localisation);
		
		localisation = new Localisation();
		localisation.setLatitude(30.20);
		localisation.setLongitude(20.20);
		localisation.setNumber("location2");
		localisation.setTracker(mytracker);
		setloc = new HashSet<>();
		setloc.add(localisation);
		localisationRepository.save(localisation);
		
		Tracker locTracker =repository.findLocalisationbyTracker(mytracker.getId());
 
		assertEquals(locTracker.getIdentification(),tracker1.getIdentification());
		assertEquals(locTracker.getInfo(),tracker1.getInfo());
		assertEquals(locTracker.getName(),tracker1.getName());
		assertEquals(locTracker.getNumber().size(),tracker1.getNumber().size());
		assertEquals(locTracker.getLocalisation().size(),2);
		
		repository.delete(locTracker);

	}
	
	@Test
	public void modifyLocation() {
		Tracker tracker1 = new Tracker();
		tracker1.setIdentification("identification1");
		tracker1.setInfo("info");
		tracker1.setName("name1");
		List<String> listnumero = new ArrayList<String>();
		listnumero.add("00003");
		listnumero.add("00001");
		listnumero.add("00002");
		tracker1.setNumber(listnumero);
		
		Tracker mytracker = repository.save(tracker1);
		
		
		Localisation localisation = new Localisation();
		localisation.setLatitude(30.20);
		localisation.setLongitude(20.20);
		localisation.setNumber("location1");
		localisation.setTracker(mytracker);
		Set<Localisation> setloc = new HashSet<>();
		setloc.add(localisation);
		localisationRepository.save(localisation);
		
		Tracker locTracker =repository.findLocalisationbyTracker(mytracker.getId());
		
		assertEquals(locTracker.getIdentification(),tracker1.getIdentification());
		assertEquals(locTracker.getInfo(),tracker1.getInfo());
		assertEquals(locTracker.getName(),tracker1.getName());
		assertEquals(locTracker.getNumber().size(),tracker1.getNumber().size());
		assertEquals(locTracker.getLocalisation().size(),1);
		
		Set<Localisation> listLoc= locTracker.getLocalisation();
		
		for(Localisation localisat:listLoc) {
			assertEquals(localisat.getLatitude(),localisation.getLatitude(),1);
			assertEquals(localisat.getNumber(),localisation.getNumber());
			assertEquals(localisat.getLongitude(),localisation.getLongitude(),1);
			assertEquals(localisat.getSpeed(),localisation.getSpeed());
			assertEquals(localisat.getDateloc(),localisation.getDateloc());
			
			
			localisat.setLatitude(10.20);
			localisat.setLongitude(10.20);
			localisat.setSpeed(100);
			localisat.setNumber("modifylocation1");
			localisationRepository.save(localisat);

		}
		
		
	   locTracker =repository.findLocalisationbyTracker(mytracker.getId());
		
		assertEquals(locTracker.getIdentification(),tracker1.getIdentification());
		assertEquals(locTracker.getInfo(),tracker1.getInfo());
		assertEquals(locTracker.getName(),tracker1.getName());
		assertEquals(locTracker.getNumber().size(),tracker1.getNumber().size());
		assertEquals(locTracker.getLocalisation().size(),1);
		
		 listLoc= locTracker.getLocalisation();
		
		for(Localisation localisat:listLoc) {
			assertEquals(localisat.getLatitude(),10.20,1);
			assertEquals(localisat.getNumber(),"modifylocation1");
			assertEquals(localisat.getLongitude(),10.20,1);
			assertEquals(localisat.getSpeed(),100);
			assertEquals(localisat.getDateloc(),localisation.getDateloc());
			
		

		}
		
		
		
		
		
		repository.delete(locTracker);
	}

}
