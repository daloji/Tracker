package com.daloji.tracker.repository;


import org.springframework.data.repository.CrudRepository;

import com.daloji.tracker.model.Credential;

public interface  CredentialRepository extends CrudRepository<Credential, Long> {

	Credential findByNumber(String number);
}
