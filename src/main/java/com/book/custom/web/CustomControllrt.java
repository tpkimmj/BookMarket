package com.book.custom.web;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.common.dto.PageDTO;
import com.book.member.dto.MemberDTO;
import com.book.product.dto.ProductDTO;
import com.book.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomControllrt {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest req, HttpServletResponse res, MemberDTO adto, Model adel, ProductDTO pdto, PageDTO pageDto) {
		MemberDTO ssKey = null;
		String page = null;
		Map<String, Object> reSet = null;
		HttpSession session = req.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin"))
				page = "redirect:/admin/";
			else page = "index";
		}
		else {
			page = "index";
		}
		
		reSet = productService.getProducts(pdto, pageDto, "best");
		adel.addAttribute("productList", reSet.get("productList"));
		return page;
	}
}
