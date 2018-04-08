package com.daloji.tracker.service;

import java.util.List;

import com.daloji.tracker.model.Localisation;

public interface LocalisationService {
 
  List<Localisation> findLocalisationByNumber(String number);
  
  List<Localisation> findLocalisationByTrackerId(long id);
 
  Localisation addLocalisation(Localisation localisation);
}
