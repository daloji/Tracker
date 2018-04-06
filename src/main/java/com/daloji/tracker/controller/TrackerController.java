package com.daloji.tracker.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daloji.tracker.model.ErrorController;
import com.daloji.tracker.model.Localisation;
import com.daloji.tracker.model.Tracker;
import com.daloji.tracker.service.TrackerServices;
import com.daloji.tracker.struct.Messages;

@RestController
@RequestMapping("/api")
public class TrackerController {
	
	@Autowired
	TrackerServices trackerService;
	
	@RequestMapping(value = "/tracker/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getTracker(@PathVariable("name") String name) {
		ResponseEntity<?> response;
		 Tracker tracker = trackerService.findTrackerByName(name);
		 if(tracker == null) {
			// ErrorController error = new ErrorController(MessageFormat.format(Messages.getString("TACKER_FIND_UNKNOW"), name));
			 ErrorController error = new ErrorController(MessageFormat.format("Tracker with name {0} not found", name));
			 response =  new ResponseEntity<ErrorController>(error, HttpStatus.NOT_FOUND);
		 }else {
			 response =  new ResponseEntity<Tracker>(tracker, HttpStatus.OK); 
		 }
	        return response;
	}
	
	
	@RequestMapping(value = "/tracker/addtracker", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Tracker tracker) {
		ResponseEntity<?> response;
		if(tracker !=null) {
			Tracker addTracker =trackerService.addTracker(tracker);
			response =  new ResponseEntity<Tracker>(addTracker, HttpStatus.OK); 
		}else {
			 ErrorController error = new ErrorController(MessageFormat.format(Messages.getString("TACKER_FIND_UNKNOW"), ""));
			 response =  new ResponseEntity<ErrorController>(error, HttpStatus.NOT_FOUND);
		}
        return response;
    }
	
	@RequestMapping(value = "/tracker/addlocalisation", method = RequestMethod.POST)
    public ResponseEntity<?> createLocalisation(@RequestBody Localisation localisation) {
		
	return null;
	}
	
}
