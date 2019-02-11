package com.sato.sosplatform.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

import com.sato.sosplatform.entities.BusinessPartner;
import com.sato.sosplatform.entities.EndCustomer;


public class MailService {
	
	private Session session;
	private Properties props;
	
	public MailService ()  {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.starttls.enable", "true");
	}
	
	public void sendGMCMail(EndCustomer ec) throws NamingException, MessagingException {
		session = Session.getInstance(props,
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("donotreply.sossite@gmail.com", "123654789qwER!");
			}
		});
		
		MimeMessage message = new MimeMessage(session);
		message.setSubject("New SOS Submission " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		String content = "A new registrant has selected your GMC as their area of operations.\n"
				+ "Please log in to SOS and register them and then check them as registered on the registration site(https://stb.satoeurope.com/SOS/en/viewregistry).\n"
				+ "\n"
				+ "Submitted: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(ec.getSubmitted()) + "\n"
				+ "Registrant Name: " + ec.getFirstName() + " " + ec.getLastName() + "\n"
				+ "Email address: " + ec.getRegistrantMail() + "\n"
				+ "Account Access Level: " + ec.getAccountLevel() + "n"
				+ "Company Name: " + ec.getCompany() + "\n"
				+ "Industrial Segment: " + ec.getIndustrialSegment() + "\n"
				+ "Country: " + ec.getCountry().getName() + "\n"
				+ "Postal Code: " + ec.getPostalCode() + "\n"
				+ "Company Address: " + ec.getAddress() + "\n"
				+ "Facilities of the printer: " + ec.getPrinterLocation() + "\n"
				+ "Divison of the Facility: " + ec.getDivisionLocation() + "\n"
				+ "General Contact Number: " + ec.getContactPhoneNumber() + "\n"
				+ "Bought the printer from: " + ec.getUnregisteredBP().getBpName() + "\n"
				+ (ec.getUnregisteredBP().getCountry() == null ? "" : "\t- Country: " + ec.getUnregisteredBP().getCountry().getName()) + "\n"
				+ (ec.getUnregisteredBP().getCountry() == null ? "" : "\t- City: " + ec.getUnregisteredBP().getCity()) + "\n"
				+ (ec.getUnregisteredBP().getCountry() == null ? "" : "\t- Postal Code: " + ec.getUnregisteredBP().getPostalCode());
		
		message.setContent(content, "text/plain; charset=utf-8");
		message.setSentDate(new Date());
		Address address = new InternetAddress(ec.getCountry().getGmc().getMail());
		message.setRecipient(Message.RecipientType.TO, address);
		
		Transport.send(message);
	}
	
	public void sendGMCMail(BusinessPartner bp) throws NamingException, MessagingException {
		session = Session.getInstance(props,
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("donotreply.sossite@gmail.com", "123654789qwER!");
			}
		});
		
		MimeMessage message = new MimeMessage(session);
		message.setSubject("New SOS Submission " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		String content = "A new registrant has selected your GMC as their area of operations.\n"
				+ "Please log in to SOS(\"insert SOS link here\") and register them and check them as registered on the registration site(https://stb.satoeurope.com/registration/en/viewregistry).\n"
				+ "\n"
				+ "Submitted: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(bp.getSubmitted()) + "\n"
				+ "Registrant Name: " + bp.getFirstName() + " " + bp.getLastName() + "\n"
				+ "Email address: " + bp.getEmail() + "\n"
				+ "Account Access Level: " + bp.getAccountLevel() + "\n"
				+ "Company Name: " + bp.getCompany() + "\n"
				+ "Country: " + bp.getCountry().getName() + "\n"
				+ "Postal Code: " + bp.getPostalCode() + "\n"
				+ "Company Address: " + bp.getCompanyAddress() + "\n"
				+ "Language: " + bp.getLanguage() + "\n"
				+ "Sub domain: " + bp.getSubDomain() + "\n"
				+ "Contract Start Date: " + new SimpleDateFormat("dd/MM/yyyy").format(bp.getContractStartDate()) + "\n"
				+ "Contract End Date: " + new SimpleDateFormat("dd/MM/yyyy").format(bp.getContractEndDate()) + "\n"
				+ "Information sharing with SATO for customer: " + (bp.isInfoSharing() == true ? "Yes" : "No") + "\n"
				+ "Company Website: " + bp.getCompanyWebsite() + "\n"
				+ "General Contact email address: " + bp.getContactEmailAddress();
		
		message.setContent(content, "text/plain; charset=utf-8");
		message.setSentDate(new Date());
		Address address = new InternetAddress(bp.getCountry().getGmc().getMail());
		message.setRecipient(Message.RecipientType.TO, address);
		
		Transport.send(message);
	}
	
}
