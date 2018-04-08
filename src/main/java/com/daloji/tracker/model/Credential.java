package com.daloji.tracker.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Credential implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6760808649530043329L;
	
	@Id
	@SequenceGenerator(name = "credential_generator", sequenceName = "credential_sequence", initialValue = 1)
	@GeneratedValue(generator = "credential_generator")
	private long id;
	
	@Column(name="status",nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@Column(name="number")
	private String number;
	
	@Column(name="operator")
	private String operator;
	
	@Column(name="nbSmsSend")
	private long nbSmsSend;
	
	@Column(name="nbSmsReceive")
	private long nbSmsReceive;
	
	@Column(name="nbSMS")
	private long nbSMS;
	
	@Column(name="nbAllSmsSend")
	private long nbAllSmsSend;
	
	@Column(name="dateStartCredit")
	private Date dateStartCredit;
	
	@Column(name="dateEndCredit")
	private Date dateEndCredit;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Tracker tracker;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public long getNbSmsSend() {
		return nbSmsSend;
	}

	public void setNbSmsSend(long nbSmsSend) {
		this.nbSmsSend = nbSmsSend;
	}

	public long getNbSmsReceive() {
		return nbSmsReceive;
	}

	public void setNbSmsReceive(long nbSmsReceive) {
		this.nbSmsReceive = nbSmsReceive;
	}

	public long getNbSMS() {
		return nbSMS;
	}

	public void setNbSMS(long nbSMS) {
		this.nbSMS = nbSMS;
	}

	public long getNbAllSmsSend() {
		return nbAllSmsSend;
	}

	public void setNbAllSmsSend(long nbAllSmsSend) {
		this.nbAllSmsSend = nbAllSmsSend;
	}

	public Date getDateStartCredit() {
		return dateStartCredit;
	}

	public void setDateStartCredit(Date dateStartCredit) {
		this.dateStartCredit = dateStartCredit;
	}

	public Date getDateEndCredit() {
		return dateEndCredit;
	}

	public void setDateEndCredit(Date dateEndCredit) {
		this.dateEndCredit = dateEndCredit;
	}

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}
	
}
