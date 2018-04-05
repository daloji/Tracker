package com.daloji.tracker.service;

import java.util.List;

import com.daloji.tracker.model.Localisation;

public interface LocalistionService {
 
  List<Localisation> findLocalisationByNumber(String number);
  
  List<Localisation> findLocalisationByTrackerId(long id);
 
  Localisation AddLocalisation(Localisation localisation);
}
