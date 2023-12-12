package com.book.product.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.book.common.dto.PageDTO;
import com.book.common.dto.RowInterPage;
import com.book.member.dto.MemberDTO;
import com.book.product.dto.ProductDTO;
import com.book.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
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
		Map<String, Object> reSet = null;
		try {
			reSet = productService.getProductList(pdto,pageDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pcnt", reSet.get("pcnt"));
		model.addAttribute("pList", reSet.get("pList"));
		model.addAttribute("contentsJsp", "/ProductMgt");
		model.addAttribute("pageDto", reSet.get("pageDto"));
		model.addAttribute("totCnt", reSet.get("totCnt"));
		model.addAttribute("pBlock", RowInterPage.PAGE_OF_BLOCK);
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
		session.setAttribute("ssKey", ssKey);
		model.addAttribute("contentsJsp", "/ProductInForm");
		return page;
	}
	
	@RequestMapping("/productMgtProc") //상품 등록 구동
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
						r = productService.insertProduct(pdto, file);
					} catch (Exception e) {
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
						r = productService.updateProduct(pdto, file);
					} catch (Exception e) {
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
	
	@RequestMapping("/productDetail") //상품 상세 정보
	public String productDetail(HttpServletRequest request, HttpServletResponse response, ProductDTO pdto, Model model, PageDTO pageDto) {
		String contentsJsp = null;
		String page = null;
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		if(mdto != null && mdto.getM_role().equals("admin")) {
				contentsJsp = "./ProductDetail";
				page = "admin/Main";
		} else {
			page = "redirect:/";
		}
		
		ProductDTO product = productService.getProduct(pdto.getP_no());
		
		model.addAttribute("product", product);
		model.addAttribute("contentsJsp", contentsJsp);
		session.setAttribute("ssKey", mdto); 
		return page;
	}
	
	@RequestMapping("/productUpForm") //상품수정
	public String productUpForm(HttpServletRequest request, HttpServletResponse response, ProductDTO pdto, Model model, PageDTO pageDto) {
		String page = null;
		MemberDTO ssKey = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) {
				ProductDTO pvo = productService.getProduct(pdto.getP_no());
				page = "admin/Main";
				model.addAttribute("pdto", pvo);
			}
			else page = "redirect:/";
		} else {
			page = "redirect:/";
		}
		// 모든 상품 리스트를 갖고 오기 
		session.setAttribute("ssKey", ssKey);
		model.addAttribute("contentsJsp", "/ProductUpForm");
		return page;
	}
	
	@RequestMapping("productDel") //상품 삭제
	public String productDel(HttpServletRequest request, HttpServletResponse response, Model model, ProductDTO pdto, PageDTO pageDto) {
		// 세션
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		String url = null;
		String msg = null;
		// 맞으면 삭제하고 삭제결과 저장 
		if(mdto != null) {
			if(mdto.getM_role().equals("mem")) { // 고객
				url = "/";
			} else if (mdto.getM_role().equals("admin")) {
				int r = productService.productDel(pdto);
				if(r>0) msg = pdto.getP_name() + "이/가 삭제되었습니다.";
				else msg = pdto.getP_name() + "이/가 삭제되지 않았습니다.";
				url = "/productMgt";
			}
		} else {
			msg = "로그인이 필요합니다.";
			url = "/login";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		session.setAttribute("ssKey", mdto); 
		return  "MsgPage";
	}
	
	@RequestMapping("/productList") //상품 리스트 페이지
	public String productList(HttpServletRequest request, HttpServletResponse response, Model model, ProductDTO pdto, PageDTO pageDto) {
		HttpSession session = request.getSession();
		String page = null;
		MemberDTO ssKey = null;
		Map<String, Object> reSet = null;
		String state = request.getParameter("state");
		pdto.setFlag(request.getParameter("flag"));
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) {
				page = "admin/Main";
				model.addAttribute("contentsJsp", "./ProductList");
			}
			else {
				page = "Main";
				model.addAttribute("contentsJsp", "custom/ProductList");
			}
		} else {
			page = "Main";
			model.addAttribute("contentsJsp", "custom/ProductList");
		}
		//상품분류에 따른 리스트 가져오기
		switch (request.getParameter("state")) {
			case "all": {
				reSet = productService.getProducts(pdto, pageDto, "all");
				break;
			}
			case "best": {
				reSet = productService.getProducts(pdto, pageDto, "best");
				break;
				}
			case "fiction": {
				reSet = productService.getProducts(pdto, pageDto, "소설");
				break;
			}
			case "cartoon": {
				reSet = productService.getProducts(pdto, pageDto, "만화");
				break;
			}
			case "divan": {
				reSet = productService.getProducts(pdto, pageDto, "시집");
				break;
			}
			case "referbook": {
				reSet = productService.getProducts(pdto, pageDto, "참고서");
				break;
			}
			case "selfdev": {
				reSet = productService.getProducts(pdto, pageDto, "자기계발");
				break;
			}
		}
		model.addAttribute("state", state);
		model.addAttribute("pcnt", reSet.get("pcnt"));
		model.addAttribute("productList", reSet.get("productList"));
		model.addAttribute("productTot", reSet.get("productTot"));
		model.addAttribute("pageDto", reSet.get("pageDto"));
		model.addAttribute("pBlock", RowInterPage.PAGE_OF_BLOCK);
		return page;
	}
	
	@RequestMapping("/bookDetail")
	public String bookDetail(HttpServletRequest request, HttpServletResponse response, Model model, ProductDTO pdto) {
		ProductDTO product = productService.getProduct(pdto.getP_no());
		
		model.addAttribute("product", product);
		model.addAttribute("contentsJsp", "custom/BookDetail");
		return "Main";
	}
	
	@RequestMapping("/search") //책 검색
	public String search(HttpServletRequest request, HttpServletResponse response, Model model, ProductDTO pdto, PageDTO pageDto) {
		HttpSession session = request.getSession();
		MemberDTO ssKey = null;
		String page = null;
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin")) {
				page = "admin/Main";
				model.addAttribute("contentsJsp", "admin/BookSearch");
			}
			else {
				page = "Main";
				model.addAttribute("contentsJsp", "custom/BookSearch");
			}
		} else {
			page = "Main";
			model.addAttribute("contentsJsp", "custom/BookSearch");
		}
		
		// 검색 단어 저장 및 flag(high/low) 저장
		if (pdto.getSearchText() == null) {
			pdto.setSearchText(request.getParameter("searchText"));
			pdto.setFlag(request.getParameter("flag"));
		}
		//검색어와 페이지 저장
		Map<String, Object> reSet = productService.bookSearch(pdto, pageDto);
		
		
		model.addAttribute("searchText", pdto.getSearchText());
		model.addAttribute("pcnt", reSet.get("pcnt"));
		model.addAttribute("productList", reSet.get("productList"));
		model.addAttribute("getSearch", reSet.get("getSearch"));
		model.addAttribute("pageDto", reSet.get("pageDto"));
		model.addAttribute("pBlock", RowInterPage.PAGE_OF_BLOCK);
		page = "Main";
		return page;
	}
	
	@ResponseBody
	@RequestMapping("/wordSearchShow")
	public List<String> wordSearchShow(HttpServletRequest request, HttpServletResponse response, ProductDTO pdto) {
				
		List<String> wordList = productService.wordSearchSHow(pdto);
		return wordList;
	}
}
