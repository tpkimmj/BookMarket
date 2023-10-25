package com.book.custom.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomControllrt {
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		
		return "index";
	}
}
