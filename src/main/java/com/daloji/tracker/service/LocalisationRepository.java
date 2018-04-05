package com.daloji.tracker.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.h2.command.dml.Merge;
import org.springframework.data.repository.CrudRepository;

import com.daloji.tracker.model.Localisation;

public interface  LocalisationRepository extends CrudRepository<Localisation, Long> {

	List<Localisation> findByNumber(String number);
	
	List<Localisation> findByTrackerId(long id);

  //  List<Localisation> findByDate(Date date);
    
    Localisation save(Localisation localisation);
    
   
}
