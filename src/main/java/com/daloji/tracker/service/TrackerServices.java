package com.daloji.tracker.service;

import com.daloji.tracker.model.Tracker;

public interface  TrackerServices {
		 
	Tracker findTrackerByName(String name);
	
	//List<Localisation> findLocalisationByName(String name);
	
	//List<Tracker> findLocalisationByName(String name);
	
	Tracker addTracker(Tracker tracker);
	
	
	void delete(Tracker tracker);
}

		 

