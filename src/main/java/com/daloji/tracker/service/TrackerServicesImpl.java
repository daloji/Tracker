package com.daloji.tracker.service;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daloji.tracker.model.Localisation;
import com.daloji.tracker.model.Tracker;
import com.daloji.tracker.repository.TrackerRepository;

@Component("trackerService")
@Transactional
public class TrackerServicesImpl implements TrackerServices{

	private final TrackerRepository trackerRepository;
	
	public  TrackerServicesImpl(TrackerRepository trackerRepo) {
		trackerRepository =trackerRepo;
	}
	
	@Override
	public Tracker addTracker(Tracker newtracker) {
		Tracker tracker = null;
		if(newtracker != null) {
			tracker = trackerRepository.save(newtracker); 
		}
		return tracker;
	}
	
	@Override
	public Tracker findTrackerByName(String name) {
		Tracker tracker = null;
		if(name !=null) {
			tracker = trackerRepository.findByName(name);
		}
		return tracker;
	}

	@Override
	public void delete(Tracker tracker) {
		trackerRepository.delete(tracker);
	}



	
	/*
	@Override
	public List<Localisation> findLocalisationByName(String name) {
		List <Localisation> listLocalisation = new ArrayList<Localisation>();
		Tracker tracker = this.findTrackerByName(name);
		if(tracker!=null) {
			listLocalisation = trackerRepository.findLocalisatiobyTracker(tracker.getId());
		}
		return listLocalisation;
	}*/

}
