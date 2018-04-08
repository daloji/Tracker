package com.daloji.tracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.daloji.tracker.exceptions.TrackerMultipleException;
import com.daloji.tracker.model.Credential;
import com.daloji.tracker.model.Localisation;
import com.daloji.tracker.model.StatusController;
import com.daloji.tracker.model.Tracker;
import com.daloji.tracker.service.CredentialService;
import com.daloji.tracker.service.LocalisationService;
import com.daloji.tracker.service.TrackerServices;
import com.daloji.tracker.utils.Utils;


@RestController
@RequestMapping("/api")
public class TrackerController {

	
	 Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageSource messageSource;


	@Autowired
	TrackerServices trackerService;

	@Autowired
	LocalisationService localisationService;

	@Autowired
	CredentialService credentialService;

	@GetMapping("/tracker/{name}")
	public ResponseEntity<?> getTracker(@PathVariable("name") String name) {
		 log.debug("get Tracker /tracker/"+name);
		ResponseEntity<?> response;
		String type = "/tracker/"+name;
		Tracker tracker = trackerService.findTrackerByName(name);
		if(tracker == null) {
			StatusController error = new StatusController("", HttpStatus.NOT_FOUND.value(),type);
			response =  new ResponseEntity<StatusController>(error, HttpStatus.NOT_FOUND);
		}else {
			response =  new ResponseEntity<Tracker>(tracker, HttpStatus.OK); 
		}
		return response;
	}

	@GetMapping("/tracker/all")
	public ResponseEntity<?> getAllTracker() {
		ResponseEntity<?> response;
		String type = "/tracker/all";
		List<Tracker> listtracker = trackerService.findAllTracker();
		if(listtracker == null) {
			StatusController error = new StatusController("", HttpStatus.NOT_FOUND.value(),type);
			response =  new ResponseEntity<StatusController>(error, HttpStatus.NOT_FOUND);
		}else {
			response =  new ResponseEntity<List<Tracker>>(listtracker, HttpStatus.OK); 
		}
		return response;
	}
	
	
	@GetMapping("/tracker/delete/{name}")
	public ResponseEntity<?> deleteTracker(@PathVariable("name") String name) {
		ResponseEntity<?> response;
		String type = "/tracker/delete/"+name;
		Tracker tracker = trackerService.deleteTrackerByName(name);
		if(tracker == null) {
			trackerService.delete(tracker);
			StatusController error = new StatusController("", HttpStatus.NOT_FOUND.value(),type);
			response =  new ResponseEntity<StatusController>(error, HttpStatus.NOT_FOUND);
		}else {
			StatusController status = new StatusController(Utils.objectToJson(tracker), HttpStatus.OK.value(),type);
			response =  new ResponseEntity<StatusController>(status, HttpStatus.OK);
		}
		return response;
	}


	@PostMapping("/tracker/addtracker")
	public ResponseEntity<?> createTracker(@RequestBody Tracker tracker) {
		ResponseEntity<?> response =null;
		String type = "/tracker/addtracker/";
		StatusController status;
		try {
			if(tracker !=null) {
				Tracker addTracker = trackerService.addTracker(tracker);
				if(addTracker != null) {
					status = new StatusController(Utils.objectToJson(addTracker), HttpStatus.CREATED.value(),type);
					response =  new ResponseEntity<StatusController>(status, HttpStatus.CREATED);
				}else {
					status = new StatusController(Utils.objectToJson(addTracker), HttpStatus.INTERNAL_SERVER_ERROR.value(),type);
					response =  new ResponseEntity<StatusController>(status, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		} catch (TrackerMultipleException e) {
			status = new StatusController("", HttpStatus.CONFLICT.value(),type);
			response =  new ResponseEntity<StatusController>(status, HttpStatus.CONFLICT);
		}


		return response;


	}

	@PostMapping("/tracker/addlocalisation")
	public ResponseEntity<?> createLocalisation(@RequestBody Localisation localisation) {
		ResponseEntity<?> response;
		String type = "/tracker/addlocalisation/";
		StatusController status;
		if(localisation.getNumber() != null) {
			Credential credential = credentialService.findCredentialByNumber(localisation.getNumber());
			if(credential!=null && credential.getTracker() != null) {
				localisation.setTracker(credential.getTracker());
				Localisation addlocalisation=localisationService.addLocalisation(localisation);
				status = new StatusController(Utils.objectToJson(addlocalisation), HttpStatus.CREATED.value(),type);
				response =  new ResponseEntity<StatusController>(status, HttpStatus.CREATED);
			}else {
				status = new StatusController("", HttpStatus.NOT_FOUND.value(),type);
				response =  new ResponseEntity<StatusController>(status, HttpStatus.NOT_FOUND);	
			}
			
		}else {
			StatusController info = new StatusController("", HttpStatus.NOT_ACCEPTABLE.value(),type);
			response =  new ResponseEntity<StatusController>(info, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		
		return response;
	}

}
