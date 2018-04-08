package com.daloji.tracker.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.daloji.tracker.model.Credential;
import com.daloji.tracker.model.Localisation;
import com.daloji.tracker.model.Status;
import com.daloji.tracker.model.Tracker;
import com.daloji.tracker.repository.CredentialRepository;
import com.daloji.tracker.repository.LocalisationRepository;
import com.daloji.tracker.repository.TrackerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackerRepositoryTest {

	@Autowired
	CredentialRepository credentialRepository;

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


		Tracker mytracker = repository.save(tracker1);
		assertEquals(mytracker.getIdentification(),tracker1.getIdentification());
		assertEquals(mytracker.getInfo(),tracker1.getInfo());
		assertEquals(mytracker.getName(),tracker1.getName());



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
		assertEquals(locTracker.getLocalisation().size(),2);

		repository.delete(locTracker);

	}

	@Test
	public void modifyLocation() {
		Tracker tracker1 = new Tracker();
		tracker1.setIdentification("identification1");
		tracker1.setInfo("info");
		tracker1.setName("name1");

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
		assertEquals(locTracker.getLocalisation().size(),1);

		List<Localisation> listLoc= locTracker.getLocalisation();

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


	@Test
	public void addCredential() {


		Tracker tracker = new Tracker();
		tracker.setIdentification("identification");
		tracker.setInfo("info");
		tracker.setName("name");

		Tracker mytracker = repository.save(tracker);

		Credential credential = new Credential();
		credential.setNumber("000001");
		credential.setNbAllSmsSend(5);;
		credential.setNbSMS(12);
		credential.setNbSmsReceive(14);
		credential.setOperator("operator");
		credential.setStatus(Status.ACTIVED);
		credential.setNbSmsSend(37);
		credential.setTracker(mytracker);

		Credential addcredential=credentialRepository.save(credential);

		assertEquals(addcredential.getNumber(),credential.getNumber());
		assertEquals(addcredential.getNbAllSmsSend(),credential.getNbAllSmsSend());
		assertEquals(addcredential.getDateEndCredit(),credential.getDateEndCredit());
		assertEquals(addcredential.getDateStartCredit(),credential.getDateStartCredit());
		assertEquals(addcredential.getStatus(),credential.getStatus());
		assertEquals(addcredential.getNbSmsReceive(),credential.getNbSmsReceive());
		assertEquals(addcredential.getNbSmsSend(),credential.getNbSmsSend());
		assertEquals(addcredential.getOperator(),credential.getOperator());	


		credential = new Credential();
		credential.setNumber("000002");
		credential.setNbAllSmsSend(51);;
		credential.setNbSMS(121);
		credential.setNbSmsReceive(114);
		credential.setOperator("operator1");
		credential.setStatus(Status.DESACTIVED);
		credential.setNbSmsSend(371);
		credential.setTracker(mytracker);

		addcredential=credentialRepository.save(credential);

		Optional<Tracker> findtracker =repository.findById(mytracker.getId());
		List<Credential> listCredent = findtracker.get().getCredential();
		
		assertEquals(listCredent.get(0).getNbSMS(),12);
		assertEquals(listCredent.get(0).getOperator(),"operator");
		assertEquals(listCredent.get(0).getStatus(),Status.ACTIVED);
		assertEquals(listCredent.get(0).getNumber(),"000001");
		assertEquals(listCredent.get(0).getNbSmsSend(),37);
		assertEquals(listCredent.get(0).getNbSmsReceive(),14);
		assertEquals(listCredent.get(0).getNbAllSmsSend(),5);
		
		
		assertEquals(listCredent.get(1).getNbSMS(),121);
		assertEquals(listCredent.get(1).getOperator(),"operator1");
		assertEquals(listCredent.get(1).getStatus(),Status.DESACTIVED);
		assertEquals(listCredent.get(1).getNumber(),"000002");
		assertEquals(listCredent.get(1).getNbSmsSend(),371);
		assertEquals(listCredent.get(1).getNbSmsReceive(),114);
		assertEquals(listCredent.get(1).getNbAllSmsSend(),51);
		
		
		assertEquals(addcredential.getNbAllSmsSend(),credential.getNbAllSmsSend());
		assertEquals(addcredential.getDateEndCredit(),credential.getDateEndCredit());
		assertEquals(addcredential.getDateStartCredit(),credential.getDateStartCredit());
		assertEquals(addcredential.getStatus(),credential.getStatus());
		assertEquals(addcredential.getNbSmsReceive(),credential.getNbSmsReceive());
		assertEquals(addcredential.getNbSmsSend(),credential.getNbSmsSend());
		assertEquals(addcredential.getOperator(),credential.getOperator());	

		repository.delete(findtracker.get());

	}
}
