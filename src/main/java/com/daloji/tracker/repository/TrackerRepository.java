package com.daloji.tracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.daloji.tracker.model.Tracker;

public interface TrackerRepository  extends CrudRepository<Tracker, Long> {
	
	Tracker findByName(String name);
	
    @Query("select tracker from Tracker tracker join fetch tracker.localisation where tracker.id =:id")
    Tracker findLocalisationbyTracker(@Param("id") Long id);

	void delete(Tracker tracker);


}
