package com.sato.sosplatform.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sato.sosplatform.entities.BusinessPartner;
import com.sato.sosplatform.entities.Countries;
import com.sato.sosplatform.entities.EndCustomer;
import com.sato.sosplatform.entities.GMC;
import com.sato.sosplatform.entities.EndCustomersBP;
import com.sato.sosplatform.repos.BusinessPartnerRepository;
import com.sato.sosplatform.repos.CountriesRepository;
import com.sato.sosplatform.repos.EndCustomerRepository;
import com.sato.sosplatform.repos.GMCRepository;
import com.sato.sosplatform.services.MailService;

@Controller
@RequestMapping(path="/en/")
public class EnController {
	
	@Autowired
	private BusinessPartnerRepository businessPartnerRepo;
	
	@Autowired
	private CountriesRepository countriesRepo;
	
	@Autowired
	private EndCustomerRepository endCustomerRepo;
	
	@Autowired
	private GMCRepository gmcRepo;
	
	MailService mailService = new MailService();

	@RequestMapping(path="registerbp", method = RequestMethod.GET)
	public String bpForm(Model model) {
		List<Countries> countryList = new ArrayList();
		for (Countries item : countriesRepo.findAll()) {
			countryList.add(item);
		}
		
		Collections.sort(countryList, Countries.COMPARE_BY_NAME);
		model.addAttribute("countryList", countryList);
		
		return "BPForm";
	}
	
	@RequestMapping(path="registerbp")
	public String registerBP(HttpServletRequest request, Model model) throws ParseException, NamingException, MessagingException {
		BusinessPartner bp = new BusinessPartner();
		
		bp.setFirstName(request.getParameter("firstName"));
		bp.setLastName(request.getParameter("lastName"));
		bp.setEmail(request.getParameter("email"));
		bp.setAccountLevel(request.getParameter("accountLevel"));
		bp.setCompany(request.getParameter("company"));
		bp.setCountry(countriesRepo.findOne(Integer.parseInt(request.getParameter("country"))));
		bp.setPostalCode(request.getParameter("postalCode"));
		bp.setCompanyAddress(request.getParameter("companyAddress"));
		bp.setLanguage(request.getParameter("language"));
		bp.setSubDomain(request.getParameter("subDomain"));
		bp.setContractStartDate(new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("contractStartDate")));
		bp.setContractEndDate(new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("contractEndDate")));
		bp.setInfoSharing(Boolean.parseBoolean(request.getParameter("infoSharing")));
		bp.setCompanyWebsite(request.getParameter("companyWebsite"));
		bp.setContactEmailAddress(request.getParameter("contactEmailAddress"));
		
		Countries country = countriesRepo.findOne(Integer.parseInt(request.getParameter("country")));
		country.addBp(bp);
		countriesRepo.save(country);
		
		mailService.sendGMCMail(bp);
		
		return "RegisterCompletion";
	}
	
	@RequestMapping(path="viewregistry")
	public String viewRegistry(HttpServletRequest request, Model model) {
		List<BusinessPartner> bpList = new ArrayList();
		List<EndCustomer> ecList = new ArrayList();
		boolean listIsRegistered = false;
		
		//Send back sorted customer list
				if (request.getParameter("gmc") != null) {
					GMC sortByGmc = gmcRepo.findByGmcName(request.getParameter("gmc"));
					for(Countries country : sortByGmc.getCountryList()) {
						for(BusinessPartner bp :country.getBpList() ) {
							if(!bp.isRegistered()) {
								bpList.add(bp);					
							}
						}
						
						for(EndCustomer ec : country.getEcList() ) {
							if(!ec.isRegistered()) {								
								ecList.add(ec);
							}
						}
					}
				//return all registered entries
				} else if(request.getParameter("showRegistered") != null) {
					if(businessPartnerRepo.findAll() != null) {
						for (BusinessPartner bp : businessPartnerRepo.getAllRegistered()) {
							bpList.add(bp);
						}
						}else {
								bpList.clear();
						}
						
						listIsRegistered = true;
					if(endCustomerRepo.findAll() != null) {
						for (EndCustomer ec : endCustomerRepo.getAllRegistered()) {
							ecList.add(ec);
						}
					}else {
						ecList.clear();
					}
				} else { 
					//return full list if no gmc query is selected
					if(businessPartnerRepo.findAll() != null) {
					for (BusinessPartner bp : businessPartnerRepo.getAllNotRegistered()) {
						bpList.add(bp);
						}
					}else {
						bpList.clear();
					}
					
					if(endCustomerRepo.findAll() != null) {
						for (EndCustomer ec : endCustomerRepo.getAllNotRegistered()) {
							ecList.add(ec);
						}
					}else {
						ecList.clear();
					}
				}
		model.addAttribute("bpList", bpList);
		model.addAttribute("ecList", ecList);
		model.addAttribute("isRegistered", listIsRegistered);
		
		return "registry";
	}
	
	@RequestMapping(path = "viewregistry", method = RequestMethod.POST)
	public String registerCustomers(HttpServletRequest request, Model model, 
			@RequestParam(value = "ecDelete" , required = false) List<String> ecDelete,
			@RequestParam(value = "bpDelete", required = false) List<String> bpDelete) {
		
		//mark End customers as registered
		if(ecDelete != null) {
			for(String id : ecDelete) {
				int intId = Integer.parseInt(id);
				EndCustomer ec = endCustomerRepo.findOne(intId);
				ec.setRegistered(true);
				endCustomerRepo.save(ec);
			}
		}
		//mark End customers as registered
		if(bpDelete != null) {
			for(String id : bpDelete) {
				int intId = Integer.parseInt(id);
				BusinessPartner bp = businessPartnerRepo.findOne(intId);
				bp.setRegistered(true);
				businessPartnerRepo.save(bp);
			}
		}
		
		List<BusinessPartner> bpList = new ArrayList();
		List<EndCustomer> ecList = new ArrayList();
		
		//Send back sorted customer list
		if (request.getParameter("gmc") != null) {
			GMC sortByGmc = gmcRepo.findByGmcName(request.getParameter("gmc"));
			for(Countries country : sortByGmc.getCountryList()) {
				for(BusinessPartner bp :country.getBpList() ) {
					if(!bp.isRegistered()) {
						bpList.add(bp);					
					}
				}
				
				for(EndCustomer ec : country.getEcList() ) {
					if(!ec.isRegistered()) {								
						ecList.add(ec);
					}
				}
			}
		} else { //return full list if no gmc query is selected
			if(businessPartnerRepo.findAll() != null) {
			for (BusinessPartner bp : businessPartnerRepo.getAllNotRegistered()) {
				bpList.add(bp);
				}
			}else {
				bpList.clear();
			}
			
			if(endCustomerRepo.findAll() != null) {
				for (EndCustomer ec : endCustomerRepo.getAllNotRegistered()) {
					ecList.add(ec);
				}
			}else {
				ecList.clear();
			}
		}
		
		model.addAttribute("bpList", bpList);
		model.addAttribute("ecList", ecList);
		
		return "registry";
	}
	
	@RequestMapping(path = "registerec", method = RequestMethod.GET)
	public String ecForm(HttpServletRequest request, Model model) {
		List<Countries> countryList = new ArrayList();
		for (Countries item : countriesRepo.findAll()) {
			item.setEcList(null);
			item.setBpList(null);
			item.setGmc(null);
			countryList.add(item);
		}
		
		Collections.sort(countryList, Countries.COMPARE_BY_NAME);
		
		model.addAttribute("countryList", countryList);
		
		return "ECForm";
	}
	
	@RequestMapping(path = "registerec", method = RequestMethod.POST)
	public String registerEc(HttpServletRequest request, Model model) {
		EndCustomer ec = new EndCustomer();
		
		ec.setFirstName(request.getParameter("firstName"));
		ec.setLastName(request.getParameter("lastName"));
		ec.setRegistrantMail(request.getParameter("registrantMail"));
		ec.setAccountLevel(request.getParameter("accountLevel"));
		ec.setCompany(request.getParameter("company"));
		ec.setIndustrialSegment(request.getParameter("industrialSegment"));
		ec.setCountry(countriesRepo.findOne(Integer.parseInt(request.getParameter("country"))));
		ec.setPostalCode(request.getParameter("postalCode"));
		ec.setAddress(request.getParameter("address"));
		ec.setPrinterLocation(request.getParameter("printerLocation"));
		ec.setDivisionLocation(request.getParameter("divisionLocation"));
		ec.setContactPhoneNumber(request.getParameter("contactPhoneNumber"));
		
		EndCustomersBP unregBp = new EndCustomersBP();
		String printerSeller = request.getParameter("printerSeller");
		if(printerSeller.equals("sato")) {
			unregBp.setBpName("SATO");
		} else {
			unregBp.setBpName(request.getParameter("bpName"));
			unregBp.setCountry(countriesRepo.findOne(Integer.parseInt(request.getParameter("country"))));
			unregBp.setCity(request.getParameter("bpCity"));
			unregBp.setPostalCode(request.getParameter("bpPostalCode"));
		}
		ec.setUnregisteredBP(unregBp);
		
		Countries country = countriesRepo.findOne(Integer.parseInt(request.getParameter("country")));
		country.addEc(ec);
		countriesRepo.save(country);
		
		return "RegisterCompletion";
	}
}
