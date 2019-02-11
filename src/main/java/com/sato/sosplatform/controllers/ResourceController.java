package com.sato.sosplatform.controllers;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/resources/")
public class ResourceController {
	
	@RequestMapping(path="sosimg", produces = MediaType.IMAGE_PNG_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<byte[]> getSosImage() throws IOException {
		ClassPathResource imgFile = new ClassPathResource("static/media/SATO-SOS-LOGO-RGB-HQ.png");
		
		byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
		return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
	}
	
	@RequestMapping(path = "termsandconditions",
			method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> getTermsAndConditions(HttpServletResponse response) throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStreamResource resource = new InputStreamResource(classLoader.getResourceAsStream("static/documents/sos_agreement(en-jp).pdf"));
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
		
		return ResponseEntity.ok().
				headers(headers).
				contentType(MediaType.APPLICATION_PDF).body(resource);
	}
	
	@RequestMapping(path = "banner", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getBanner (HttpServletResponse response) throws IOException {
		ClassPathResource imgFile = new ClassPathResource("static/media/sato.png");
		
		byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
		
		return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
	}
	
}
