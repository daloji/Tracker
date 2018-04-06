package com.daloji.tracker.service;

import com.daloji.tracker.model.Credential;

public interface CredentialService {

  Credential findCredentialByNumber(String number);
}
