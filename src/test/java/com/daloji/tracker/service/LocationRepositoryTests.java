package com.daloji.tracker.service;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.daloji.tracker.model.Localisation;
import com.daloji.tracker.repository.LocalisationRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationRepositoryTests {

	@Autowired
	LocalisationRepository repository;

	//@Test
	public void findLocalisation() {
		//List<Localisation> listlocalisation= repository.findByNumber("phoneNumber");
		//assertEquals(listlocalisation.size(),1);
	}

	@Test
	public void addLocalisation() {
		Localisation localisation = new Localisation();
		localisation.setLatitude(30.1);
		localisation.setNumber("number");

		Localisation newlocalisation= repository.save(localisation);
		assertEquals(newlocalisation.getNumber(),localisation.getNumber());

		List<Localisation> listlocalisation= repository.findByNumber("number");
		assertEquals(listlocalisation.size(),1);
		Localisation loc =listlocalisation.get(0);
		assertEquals(localisation.getNumber(),loc.getNumber());
		assertEquals(localisation.getLatitude(),loc.getLatitude(),1);
		assertEquals(localisation.getLongitude(),loc.getLongitude(),1);
		assertEquals(localisation.getId(),loc.getId());
		assertEquals(localisation.getDateloc(),loc.getDateloc());
		assertEquals(localisation.getSpeed(),loc.getSpeed());



		localisation = new Localisation();
		localisation.setLatitude(30);
		localisation.setNumber("number");

		newlocalisation= repository.save(localisation);
		assertEquals(newlocalisation.getNumber(),localisation.getNumber());

		listlocalisation= repository.findByNumber("number");
		assertEquals(listlocalisation.size(),2);
		loc =listlocalisation.get(0);
		assertEquals(localisation.getNumber(),loc.getNumber());
		assertEquals(localisation.getLatitude(),loc.getLatitude(),1);
		assertEquals(localisation.getLongitude(),loc.getLongitude(),1);
		assertEquals(localisation.getId(),loc.getId(),1);
		assertEquals(localisation.getDateloc(),loc.getDateloc());
		assertEquals(localisation.getSpeed(),loc.getSpeed());
		loc =listlocalisation.get(1);
		assertEquals(localisation.getNumber(),loc.getNumber());
		assertEquals(localisation.getLatitude(),loc.getLatitude(),1);
		assertEquals(localisation.getLongitude(),loc.getLongitude(),1);
		assertEquals(localisation.getId(),loc.getId());
		assertEquals(localisation.getDateloc(),loc.getDateloc());
		assertEquals(localisation.getSpeed(),loc.getSpeed(),1);
	}



}
