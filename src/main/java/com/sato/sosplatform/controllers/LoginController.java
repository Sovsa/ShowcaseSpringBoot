package com.sato.sosplatform.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(path = "/login")
	public String login(HttpServletRequest request, Model model) {
		
	return "login";
	}
}
