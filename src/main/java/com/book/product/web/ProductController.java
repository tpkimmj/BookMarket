package com.book.product.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.book.common.dto.PageDTO;
import com.book.member.dto.MemberDTO;
import com.book.product.dto.ProductDTO;
import com.book.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productServise;
	@Value("${resources.location}")
	String resourcesLocation;
	
	@RequestMapping("/productMgt")
	public String productMgr(HttpServletRequest request, HttpServletResponse response, ProductDTO pdto, Model model, PageDTO pageDto) {
		String page = null;
		MemberDTO ssKey = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) {
				page = "admin/Main";
			}
			else page = "redirect:/";
		} else {
			page = "redirect:/";
		}
		// 모든 상품 리스트를 갖고 오기 
		Map<String, Object> reSet = null;
		try {
			reSet = productServise.getProductList(pageDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("pcnt", reSet.get("pcnt"));
		// 헤딩 리스트를 저장("productList")
		model.addAttribute("pList", reSet.get("pList"));
		model.addAttribute("contentsJsp", "/ProductMgt");
		session.setAttribute("ssKey", ssKey);
		return page;
	}
	
	@RequestMapping("/productInForm")
	public String productInForm(HttpServletRequest request, HttpServletResponse response, ProductDTO pdto, Model model, PageDTO pageDto) {
		String page = null;
		MemberDTO ssKey = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin"))
				page = "admin/Main";
			else page = "redirect:/";
		} else {
			page = "redirect:/";
		}
		// 모든 상품 리스트를 갖고 오기 
		session.setAttribute("ssKey", ssKey);
		model.addAttribute("contentsJsp", "/ProductInForm");
		return page;
	}
	
	@RequestMapping("/productMgtProc")
	public String productInsert(HttpServletRequest request, HttpServletResponse response, ProductDTO pdto, Model model, PageDTO pageDto, @RequestParam("image2") MultipartFile file) {
		MemberDTO ssKey = null;
		String url = null;
		String msg = null;
		int r = 0;
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			
			if(ssKey.getM_role().equals("admin")) {
				// 실제로 작업(flag따라서 insert와 update)
				String flag = request.getParameter("flag");
				if(flag.equals("insert")) {
					// 업로드 경로 저장
					pdto.setPath(resourcesLocation);
					try {
						r = productServise.insertProduct(pdto, file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(r>0) {
						msg = "상품등록성공";
					} else {
						msg = "상품등록실패";
					}
					url = "productMgt";
				} else if (flag.equals("update")) {
					pdto.setPath(resourcesLocation);
					try {
						r = productServise.updateProduct(pdto, file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(r>0) {
						msg = "상품수정성공";
					} else {
						msg = "상품수정실패";
					}
					url = "productMgt";
				}
			} else {
				url = "redirect:/";
				msg = "잘못된 경로 접근입니다.";
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		session.setAttribute("ssKey", ssKey);
		return "MsgPage";
	}
	
	@RequestMapping("/productDetail")
	public String productDetail(HttpServletRequest request, HttpServletResponse response, ProductDTO pdto, Model model, PageDTO pageDto) {
		String contentsJsp = null;
		String page = null;
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		if(mdto != null) {
			if(mdto.getM_role().equals("mem")) { // 고객
				contentsJsp = "custom/ProductDetail";
				page = "Main";
			} else if (mdto.getM_role().equals("admin")) {
				contentsJsp = "./ProductDetail";
				page = "admin/Main";
			}
		} else {
			contentsJsp = "custom/ProductDetail";
			page = "/Main";
		}
		
		ProductDTO product = productServise.getProduct(pdto.getP_no());
		
		model.addAttribute("product", product);
		model.addAttribute("contentsJsp", contentsJsp);
		session.setAttribute("ssKey", mdto); 
		return page;
	}
	
	
}
