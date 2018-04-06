package com.daloji.tracker.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daloji.tracker.model.Credential;
import com.daloji.tracker.repository.CredentialRepository;

@Component("credentialService")
@Transactional
public class CredentialServiceImpl implements CredentialService {

	
	private final CredentialRepository credentialrepository;
	
	
	public CredentialServiceImpl(CredentialRepository repo) {
		credentialrepository = repo;
	}
	@Override
	public Credential findCredentialByNumber(String number) {
		return credentialrepository.findByNumber(number);
	}

}
