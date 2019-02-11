package com.sato.sosplatform.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(
		  name = "tableGen",
		  allocationSize = 1,
		  initialValue = 1)
public class EndCustomersBP {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	private int id;
	private String bpName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Countries country;
	private String city;
	private String postalCode;
	
	public EndCustomersBP(String bpName, Countries country,
			String city, String postalCode) {
		super();
		this.bpName = bpName;
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
	}

	public EndCustomersBP(Countries country, String city, String postalCode) {
		super();
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
	}
	
	public EndCustomersBP() {
		super();
	}

	public String getBpName() {
		return bpName;
	}

	public void setBpName(String bpName) {
		this.bpName = bpName;
	}

	public int getId() {
		return id;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
