package com.daloji.tracker.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.daloji.tracker.model.Credential;
import com.daloji.tracker.model.Status;
import com.daloji.tracker.repository.CredentialRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CredentialRepositoryTest {
	
	@Autowired
	CredentialRepository credentialRepo;
	
	@Test
	public void addCredential() {
		
		Credential credential = new Credential();
		credential.setNumber("000001");
		credential.setNbAllSmsSend(5);;
		credential.setNbSMS(12);
		credential.setNbSmsReceive(14);
		credential.setOperator("operator");
		credential.setStatus(Status.ACTIVED);
		credential.setNbSmsSend(37);
		
		Credential addcredential=credentialRepo.save(credential);
		
		assertEquals(addcredential.getNumber(),credential.getNumber());
		assertEquals(addcredential.getNbAllSmsSend(),credential.getNbAllSmsSend());
		assertEquals(addcredential.getDateEndCredit(),credential.getDateEndCredit());
		assertEquals(addcredential.getDateStartCredit(),credential.getDateStartCredit());
		assertEquals(addcredential.getStatus(),credential.getStatus());
		assertEquals(addcredential.getNbSmsReceive(),credential.getNbSmsReceive());
		assertEquals(addcredential.getNbSmsSend(),credential.getNbSmsSend());
		assertEquals(addcredential.getOperator(),credential.getOperator());	
		
		credential = new Credential();
		credential.setNumber("000002");
		credential.setNbAllSmsSend(51);;
		credential.setNbSMS(121);
		credential.setNbSmsReceive(114);
		credential.setOperator("operator1");
		credential.setStatus(Status.DESACTIVED);
		credential.setNbSmsSend(371);
		
		addcredential=credentialRepo.save(credential);
		
		assertEquals(addcredential.getNumber(),credential.getNumber());
		assertEquals(addcredential.getNbAllSmsSend(),credential.getNbAllSmsSend());
		assertEquals(addcredential.getDateEndCredit(),credential.getDateEndCredit());
		assertEquals(addcredential.getDateStartCredit(),credential.getDateStartCredit());
		assertEquals(addcredential.getStatus(),credential.getStatus());
		assertEquals(addcredential.getNbSmsReceive(),credential.getNbSmsReceive());
		assertEquals(addcredential.getNbSmsSend(),credential.getNbSmsSend());
		assertEquals(addcredential.getOperator(),credential.getOperator());	
		
		addcredential=credentialRepo.findByNumber("000002");
		assertEquals(addcredential.getNumber(),"000002");
		assertEquals(addcredential.getOperator(),"operator1");
		assertEquals(addcredential.getNbAllSmsSend(),51);
		assertEquals(addcredential.getStatus(),Status.DESACTIVED);
		assertEquals(addcredential.getNbSmsReceive(),114);
		assertEquals(addcredential.getNbSmsSend(),371);
		assertEquals(addcredential.getNbSMS(),121);
		
		credentialRepo.delete(addcredential);
		
		
		addcredential=credentialRepo.findByNumber("000001");
		assertEquals(addcredential.getNumber(),"000001");
		assertEquals(addcredential.getOperator(),"operator");
		assertEquals(addcredential.getNbAllSmsSend(),5);
		assertEquals(addcredential.getStatus(),Status.ACTIVED);
		assertEquals(addcredential.getNbSmsReceive(),14);
		assertEquals(addcredential.getNbSmsSend(),37);
		credentialRepo.delete(addcredential);
		
		
	}
	
	@Test
	public void modifyCredential() {
		
		Credential credential = new Credential();
		credential.setNumber("000001");
		credential.setNbAllSmsSend(5);;
		credential.setNbSMS(12);
		credential.setNbSmsReceive(14);
		credential.setOperator("operator");
		credential.setStatus(Status.ACTIVED);
		credential.setNbSmsSend(37);
		
		Credential addcredential=credentialRepo.save(credential);
		
		assertEquals(addcredential.getNumber(),credential.getNumber());
		assertEquals(addcredential.getNbAllSmsSend(),credential.getNbAllSmsSend());
		assertEquals(addcredential.getDateEndCredit(),credential.getDateEndCredit());
		assertEquals(addcredential.getDateStartCredit(),credential.getDateStartCredit());
		assertEquals(addcredential.getStatus(),credential.getStatus());
		assertEquals(addcredential.getNbSmsReceive(),credential.getNbSmsReceive());
		assertEquals(addcredential.getNbSmsSend(),credential.getNbSmsSend());
		assertEquals(addcredential.getOperator(),credential.getOperator());	
		
		
		addcredential.setOperator("operator");
		Credential modifyCredential=credentialRepo.save(credential);
		assertEquals(addcredential.getOperator(),modifyCredential.getOperator());	
		
		
		addcredential=credentialRepo.findByNumber("000001");
		assertEquals(addcredential.getOperator(),modifyCredential.getOperator());
		credentialRepo.delete(addcredential);
		
	}

}
