package com.book.pay.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.book.pay.service.ApiService;

@Controller
public class PayController {
	
	@Autowired
	private ApiService apiService;
	
	
	@RequestMapping("/cancel")
	public String cancel(@RequestParam Map<String, String> param) {
		Map<String, String> map = new HashMap<>();
		String url = "https://api.testpayup.co.kr/v2/api/payment/himedia/cancel2";
		//http://localhost:8000/cancel?transactionId=20231129155815ST0423&merchantId=himedia
		//화며에서 데이터 보내고 그 값을 가지고 취소하기
		
		//취소하는거 만들기
    	String merchantId = param.get("merchantId");
		String transactionId = param.get("transactionId");
		String signature = "";
		
		map.put("merchantId", merchantId);
		map.put("transactionId", transactionId);
		try {
			signature = apiService.getSHA256Hash(merchantId+"|"+transactionId+"|"+"ac805b30517f4fd08e3e80490e559f8e");
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("signature", signature);
		
		apiService.JsonApi(url, map);
	    
		return "";
	}
	
	@RequestMapping("/auth")
	public ModelAndView authForm(@RequestParam Map<String,String> param) {
		// 현재 날짜/시간       
		LocalDateTime now = LocalDateTime.now();         
		// 현재 날짜/시간 출력        
		// 2021-06-17T06:43:21.419878100          
		// 포맷팅        
		@SuppressWarnings("unused")
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));         
		// 포맷팅 현재 날짜/시간 출력        
		
		ModelAndView mav = new ModelAndView();
		
		//주문요청 API 요청
		String merchantId = "himedia"; //가맹점 아이디
		String orderNumber = param.get("p_no"); //가맹점 아이디 //o_no받아오기
		String amount = param.get("amount"); //amount 받아오기
		String itemName = param.get("p_name"); //p_name 받아오기
		String userName = param.get("mem_id"); //mem_id 받아오기
		String userAgent = "WP";
		String returnUrl = "returnUrl";
		String signature = "";
		String timestamp = formatedNow;
		String g = "|";
		String apiCertKey = "ac805b30517f4fd08e3e80490e559f8e";
		
		
		try {
//			({merchantId}|{orderNumber}|{amount}|{apiCertKey}|{timestamp}
			signature = apiService.getSHA256Hash(merchantId+g+orderNumber+g+amount+g+apiCertKey+g+timestamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = "https://api.testpayup.co.kr/ap/api/payment/"+merchantId+"/order";
		Map<String, String> map = new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("orderNumber", orderNumber);
		map.put("amount", amount);
		map.put("itemName", itemName);
		map.put("userName", userName);
		map.put("userAgent", userAgent);
		map.put("returnUrl", returnUrl);
		map.put("timestamp", timestamp);
		map.put("signature", signature);
		
		Map<String,Object> orderResult = apiService.JsonApi(url, map);
		mav.addObject("orderResult",orderResult); // <- 화면으로 데이터보내기
		
		return mav;
	}
	
	@RequestMapping("/authPay")
	public ModelAndView authPay(@RequestParam Map<String,String> param) {
		
		ModelAndView mav = new ModelAndView();
		System.err.println("=====>"+param);
		//화면에서 넘어온값 확인 해보기
		Map<String, Object> getOrder = apiService.getOrder(param);
		param.put("orderNumber", String.valueOf(getOrder.get("O_NO")));
		//결제 요청 완성 하기
		//3.3 결제요청 하기 -- 규격서 참고
		String res_cd = param.get("res_cd");
		String res_msg = param.get("res_msg");
		String enc_data = param.get("enc_data");
		String enc_info = param.get("enc_info");
		String tran_cd = param.get("tran_cd");
		String buyr_mail = param.get("buyr_mail");
		String ordr_idxx = param.get("ordr_idxx");
		
		String url = "https://api.testpayup.co.kr/ap/api/payment/"+ordr_idxx+"/pay";
		Map<String, String> map = new HashMap<>();
		map.put("res_cd", res_cd);
		map.put("res_msg", res_msg);
		map.put("enc_data", enc_data);
		map.put("enc_info", enc_info);
		map.put("tran_cd", tran_cd);
		map.put("buyr_mail", buyr_mail);
		map.put("ordr_idxx", ordr_idxx);
		
		Map<String,Object> orderResult = apiService.JsonApi(url, map);
		mav.addObject("orderResult",orderResult); // <- 화면으로 데이터보내기
		apiService.payResult(orderResult);
		apiService.deleteCart(param);
		apiService.updateState(param);
		mav.setViewName("pay");
		
		return mav;
	}
}
