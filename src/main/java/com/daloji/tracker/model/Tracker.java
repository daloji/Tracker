package com.daloji.tracker.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Tracker  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name = "tracker_generator", sequenceName = "tracker_sequence", initialValue = 1)
	@GeneratedValue(generator = "tracker_generator")
	private long id;
	
	@Column(name="name",unique=true)
	private String name;
	
	@Column(name="credential")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tracker", cascade = CascadeType.ALL)
	private List<Credential> credential;
	
	@Column(name="identification")
	private String identification;
	
	@Column(name="info")
	private String info;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tracker", cascade = CascadeType.ALL)
	private Set<Localisation> localisation;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getIdentification() {
		return identification;
	}


	public void setIdentification(String identification) {
		this.identification = identification;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public Set<Localisation> getLocalisation() {
		return localisation;
	}


	public void setLocalisation(Set<Localisation> localisation) {
		this.localisation = localisation;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Credential> getCredential() {
		return credential;
	}


	public void setCredential(List<Credential> credential) {
		this.credential = credential;
	}

}
