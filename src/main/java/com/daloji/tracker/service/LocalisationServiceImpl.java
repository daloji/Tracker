package com.daloji.tracker.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daloji.tracker.model.Localisation;

@Component("localisationService")
@Transactional
public class LocalisationServiceImpl  implements LocalistionService{
   
	private final LocalisationRepository localisationRepository;
	
	private static final String STRING_EMPTY="";
	
	
	
	public LocalisationServiceImpl(LocalisationRepository locRepo) {
		localisationRepository = locRepo;
	}
 
	@Override
	public List<Localisation> findLocalisationByNumber(String number) {
		List<Localisation>  lisstLocalisation = null;
		if(number != null && !STRING_EMPTY.equals(number)) {
			lisstLocalisation = localisationRepository.findByNumber(number);
		}
		return lisstLocalisation;
	}

	@Override
	public Localisation AddLocalisation(Localisation localisation) {
		Localisation newlocalisation = null;
		if(localisation != null) {
			newlocalisation = localisationRepository.save(localisation);
		}

		return newlocalisation;
	}

	@Override
	public List<Localisation> findLocalisationByTrackerId(long id) {
		return localisationRepository.findByTrackerId(id);
	}

}
