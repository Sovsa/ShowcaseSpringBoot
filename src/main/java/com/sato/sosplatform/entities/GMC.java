package com.sato.sosplatform.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(
		  name = "tableGen",
		  allocationSize = 1,
		  initialValue = 1)
public class GMC {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="tableGen")
	private int id;
	private String gmcName;
	private String mail;
	
	public GMC(String gmcName, String mail, List<Countries> countryList) {
		super();
		this.gmcName = gmcName;
		this.mail = mail;
		this.countryList = countryList;
	}

	@OneToMany(targetEntity = Countries.class, cascade = CascadeType.ALL)
	private List<Countries> countryList = new ArrayList();

	public GMC(String gmcName) {
		super();
		this.gmcName = gmcName;
	}
	
	public GMC(){
		super();
	}

	public int getId() {
		return id;
	}

	public String getGmcName() {
		return gmcName;
	}

	public void setGmcName(String gmcName) {
		this.gmcName = gmcName;
	}

	public List<Countries> getCountryList() {
		return countryList;
	}

	public void setCountryList(List countryList) {
		this.countryList = countryList;
	}
	
	public void setCountryListWithGMC(List<Countries> countryList) {
		this.countryList = countryList;
		for(Countries country : this.countryList) {
			country.setGmc(this);
		}
	}
	
	public void addCountry(String country){
		countryList.add(new Countries(country));
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}