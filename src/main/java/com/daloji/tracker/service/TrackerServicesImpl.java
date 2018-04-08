package com.daloji.tracker.service;


import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daloji.tracker.exceptions.TrackerMultipleException;
import com.daloji.tracker.model.Credential;
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

	@Override
	public Tracker addTracker(Tracker newtracker) throws TrackerMultipleException {	
		Tracker track = null;
		if(newtracker != null){
			track = trackerRepository.findByName(newtracker.getName());
			if(track != null) {
				throw new TrackerMultipleException("Multiple tracker");
			}
			
			if(newtracker.getCredential() != null) {
				for(Credential credential:newtracker.getCredential()) {
					credential.setTracker(newtracker);
				}
			}
			track = trackerRepository.save(newtracker); 
		}
		return track;
	}

	@Override
	public Tracker deleteTrackerByName(String name) {
		Tracker tracker =null;
		if(name !=null) {

			tracker=trackerRepository.findByName(name);
			if(tracker !=null) {
				trackerRepository.delete(tracker);
			}
		}
		return tracker;
	}

	@Override
	public List<Tracker> findAllTracker() {
		return (List<Tracker>) trackerRepository.findAll();
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
