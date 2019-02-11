package com.sato.sosplatform.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(
		  name = "tableGen",
		  allocationSize = 1,
		  initialValue = 1)
public class Countries {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	private int id;
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gmc_id")
	private GMC gmc;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "country")
	private List<BusinessPartner> bpList;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "country")
	private List<EndCustomer> ecList;

	public Countries(String country) {
		super();
		this.name = country;
	}
	
	public Countries(){
		super();
	}
	
	public int getId(){
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String country) {
		this.name = country;
	}

	public List<BusinessPartner> getBpList() {
		return bpList;
	}

	public void setBpList(List<BusinessPartner> bpList) {
		this.bpList = bpList;
	}

	public List<EndCustomer> getEcList() {
		return ecList;
	}

	public void setEcList(List<EndCustomer> ecList) {
		this.ecList = ecList;
	}
	
	public GMC getGmc() {
		return gmc;
	}

	public void setGmc(GMC gmc) {
		this.gmc = gmc;
	}

	public void addBp(BusinessPartner bp){
		this.bpList.add(bp);
	}
	
	public void addEc(EndCustomer ec){
		this.ecList.add(ec);
	}
	
	public static Comparator<Countries> COMPARE_BY_NAME = new Comparator<Countries>(){
		public int compare(Countries one, Countries other){
			return one.name.compareTo(other.name);
		}
	};
}
