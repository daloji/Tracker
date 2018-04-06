package com.daloji.tracker.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.daloji.tracker.model.Localisation;

public interface  LocalisationRepository extends CrudRepository<Localisation, Long> {

	List<Localisation> findByNumber(String number);
	
	List<Localisation> findByTrackerId(long id);

   
}
