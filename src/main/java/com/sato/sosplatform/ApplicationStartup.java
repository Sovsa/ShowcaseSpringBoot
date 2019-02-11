package com.sato.sosplatform;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sato.sosplatform.entities.Countries;
import com.sato.sosplatform.entities.GMC;
import com.sato.sosplatform.repos.CountriesRepository;
import com.sato.sosplatform.repos.GMCRepository;

@Component
public class ApplicationStartup {
	
	@Autowired
	private GMCRepository gmcRepo;
	@Autowired
	private CountriesRepository countriesRepo;

	@PostConstruct
	public void onApplicationInit() {
		System.out.println("ApplicationInit()");
		if(!gmcRepo.exists(1)){
			// Nordic
			List<Countries> nordicList = new ArrayList<Countries>();
			nordicList.add(new Countries("Sweden"));
			nordicList.add(new Countries("Finland"));
			nordicList.add(new Countries("Norway"));
			nordicList.add(new Countries("Denmark"));
			nordicList.add(new Countries("Estonia"));
			nordicList.add(new Countries("Latvia"));
			nordicList.add(new Countries("Lithuania"));
			GMC nordic = new GMC("Nordic");
			nordic.setMail("support-nordic@sato-global.com");
			nordic.setCountryListWithGMC(nordicList);
			
			//SUL
			List<Countries> sulList = new ArrayList<Countries>();
			sulList.add(new Countries ("United Kingdom"));
			sulList.add(new Countries ("Ireland"));
			GMC sul = new GMC("SUL");
			sul.setMail("techsupport-uk@sato-global.com");
			sul.setCountryListWithGMC(sulList);
			
			//SEGB
			List<Countries> sbbList = new ArrayList<Countries>();
			sbbList.add(new Countries ("Netherlands"));
			sbbList.add( new Countries ("Belgium"));
			sbbList.add(new Countries ("Luxemburg"));
			GMC sbb = new GMC("SEGB");
			sbb.setMail("technical.support-nl@sato-global.com");
			sbb.setCountryListWithGMC(sbbList);
			
			//SFS
			List<Countries> sfsList = new ArrayList<Countries>();
			sfsList.add(new Countries("France"));
			GMC sfs = new GMC("SFS");
			sfs.setMail("sav-fr@sato-global.com");
			sfs.setCountryListWithGMC(sfsList);
			
			//SEGS
			List<Countries> sisList = new ArrayList<Countries>();
			sisList.add(new Countries("Spain"));
			sisList.add(new Countries("Portugal"));
			GMC sis = new GMC("SEGS");
			sis.setMail("sat-es@sato-global.com");
			sis.setCountryListWithGMC(sisList);
			
			//SEGI
			List<Countries> sggItalyList = new ArrayList<Countries>();
			sggItalyList.add(new Countries("Italy"));
			sggItalyList.add(new Countries("Greece"));
			sggItalyList.add(new Countries("Turkey"));
			sggItalyList.add(new Countries("Israel"));
			GMC sggItaly = new GMC("SEGI");
			sggItaly.setMail("support-eu@sato-global.com");
			sggItaly.setCountryListWithGMC(sggItalyList);
			
			//SEGD
			List<Countries> segdList = new ArrayList<Countries>();
			segdList.add(new Countries("Germany"));
			segdList.add(new Countries("Switzerland"));
			segdList.add(new Countries("Austria"));
			segdList.add(new Countries("Slovenia"));
			segdList.add(new Countries("Croatia"));
			segdList.add(new Countries("Bosnia and Herzegovina"));
			segdList.add(new Countries("Serbia"));
			segdList.add(new Countries("Montenegro"));
			segdList.add(new Countries("Kosovo"));
			segdList.add(new Countries("Macedonia"));
			segdList.add(new Countries("Albania"));
			segdList.add(new Countries("Bulgaria"));
			segdList.add(new Countries("Czech Republic"));
			segdList.add(new Countries("Slovakia"));
			segdList.add(new Countries("Hungary"));
			segdList.add(new Countries("Romania"));
			segdList.add(new Countries("Ukraine"));
			segdList.add(new Countries("Belarus"));
			segdList.add(new Countries("Russia"));
			GMC sgg = new GMC("SEGD");
			sgg.setMail("service-de@sato-global.com");
			sgg.setCountryListWithGMC(segdList);
			
			//SPZ
			List<Countries> spzList = new ArrayList<Countries>();
			spzList.add(new Countries ("Poland"));
			GMC spz = new GMC("SPZ");
			spz.setMail("tech-pl@sato-global.com");
			spz.setCountryListWithGMC(spzList);
			
			gmcRepo.save(nordic);
			gmcRepo.save(sul);
			gmcRepo.save(sfs);
			gmcRepo.save(sis);
			gmcRepo.save(sbb);
			gmcRepo.save(sgg);
			gmcRepo.save(sggItaly);
			gmcRepo.save(spz);
			
			
		}
	}
}
