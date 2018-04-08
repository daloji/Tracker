package com.daloji.tracker.service;

import java.util.List;

import com.daloji.tracker.exceptions.TrackerMultipleException;
import com.daloji.tracker.model.Tracker;

public interface  TrackerServices {
		 
	Tracker findTrackerByName(String name);
	
	//List<Localisation> findLocalisationByName(String name);
	
	//List<Tracker> findLocalisationByName(String name);
	
	List<Tracker> findAllTracker();
	
	Tracker addTracker(Tracker track) throws TrackerMultipleException;
	
	Tracker deleteTrackerByName(String name);
	
	void delete(Tracker tracker);
}

		 

