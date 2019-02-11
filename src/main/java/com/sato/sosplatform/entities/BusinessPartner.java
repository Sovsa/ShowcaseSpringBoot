package com.sato.sosplatform.entities;

import java.util.Date;

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
public class BusinessPartner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String accountLevel;
	private String company;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Countries country;
	private String postalCode;
	private String companyAddress;
	private String language;
	private String subDomain;
	private Date contractStartDate;
	private Date contractEndDate;
	private boolean infoSharing;
	private String companyWebsite;
	private String contactEmailAddress;
	private boolean registered;
	private Date submitted;
	
	public BusinessPartner(String firstName, String lastName,
			String email, String accountLevel, String company, Countries country,
			String postalCode, String companyAddress, String language,
			String subDomain, Date contractStartDate, Date contractEndDate,
			boolean infoSharing, String companyWebsite,
			String contactEmailAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accountLevel = accountLevel;
		this.company = company;
		this.country = country;
		this.postalCode = postalCode;
		this.companyAddress = companyAddress;
		this.language = language;
		this.subDomain = subDomain;
		this.contractStartDate = contractStartDate;
		this.contractEndDate = contractEndDate;
		this.infoSharing = infoSharing;
		this.companyWebsite = companyWebsite;
		this.contactEmailAddress = contactEmailAddress;
		this.submitted = new Date();
	}
	
	public BusinessPartner(){
		super();
		this.submitted = new Date();
	}
	
	public int getId() {
		return id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSubDomain() {
		return subDomain;
	}
	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public Date getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public boolean isInfoSharing() {
		return infoSharing;
	}

	public void setInfoSharing(boolean infoSharing) {
		this.infoSharing = infoSharing;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getContactEmailAddress() {
		return contactEmailAddress;
	}
	public void setContactEmailAddress(String contactEmailAddress) {
		this.contactEmailAddress = contactEmailAddress;
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
