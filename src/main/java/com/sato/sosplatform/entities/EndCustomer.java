package com.sato.sosplatform.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(
		  name = "tableGen",
		  allocationSize = 1,
		  initialValue = 1)
public class EndCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	private int id;
	private String firstName;
	private String lastName;
	private String registrantMail;
	private String accountLevel;
	private String company;
	private String industrialSegment;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Countries country;
	private String postalCode;
	private String address;
	private String printerLocation;
	private String divisionLocation;
	private String contactPhoneNumber;
	private boolean registered;
	private Date submitted;
	
	@OneToOne(targetEntity = EndCustomersBP.class, cascade=CascadeType.ALL)
	private EndCustomersBP unregisteredBP;
	
	public EndCustomer(int id, String firstName, String lastName,
			String registrantMail, String accountLevel, String company,
			String industrialSegment, Countries country, String postalCode,
			String address, String printerLocation, String divisionLocation,
			String contactPhoneNumber, EndCustomersBP unregisteredBP) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrantMail = registrantMail;
		this.accountLevel = accountLevel;
		this.company = company;
		this.industrialSegment = industrialSegment;
		this.country = country;
		this.postalCode = postalCode;
		this.address = address;
		this.printerLocation = printerLocation;
		this.divisionLocation = divisionLocation;
		this.contactPhoneNumber = contactPhoneNumber;
		this.unregisteredBP = unregisteredBP;
		this.submitted = new Date();
	}

	public EndCustomer(String firstName, String lastName, String registrantMail,
			String accountLevel, String company, String industrialSegment,
			Countries country, String postalCode, String address,
			String printerLocation, String divisionLocation,
			String contactPhoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrantMail = registrantMail;
		this.accountLevel = accountLevel;
		this.company = company;
		this.industrialSegment = industrialSegment;
		this.country = country;
		this.postalCode = postalCode;
		this.address = address;
		this.printerLocation = printerLocation;
		this.divisionLocation = divisionLocation;
		this.contactPhoneNumber = contactPhoneNumber;
		this.submitted = new Date();
		}
	
	public EndCustomer(){
		super();
		this.submitted = new Date();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getId() {
		return id;
	}
	public String getRegistrantMail() {
		return registrantMail;
	}
	public void setRegistrantMail(String registrantMail) {
		this.registrantMail = registrantMail;
	}
	public String getAccountLevel() {
		return accountLevel;
	}
	public void setAccountLevel(String accountLevel) {
		this.accountLevel = accountLevel;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getIndustrialSegment() {
		return industrialSegment;
	}
	public void setIndustrialSegment(String industrialSegment) {
		this.industrialSegment = industrialSegment;
	}
	public Countries getCountry() {
		return country;
	}
	public void setCountry(Countries country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrinterLocation() {
		return printerLocation;
	}
	public void setPrinterLocation(String printerLocation) {
		this.printerLocation = printerLocation;
	}
	public String getDivisionLocation() {
		return divisionLocation;
	}
	public void setDivisionLocation(String divisionLocation) {
		this.divisionLocation = divisionLocation;
	}
	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}
	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public EndCustomersBP getUnregisteredBP() {
		return unregisteredBP;
	}

	public void setUnregisteredBP(EndCustomersBP unregisteredBP) {
		this.unregisteredBP = unregisteredBP;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = new Date();
	}
}
