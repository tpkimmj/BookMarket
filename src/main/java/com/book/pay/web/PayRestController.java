package com.book.pay.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.pay.service.ApiService;

@RestController
public class PayRestController {

	@Autowired
	private ApiService apiService;
	
	@RequestMapping("/payOrder")
	public Map<String, Object> payOrder(@RequestParam Map<String, String> param) {
		
		Map<String, Object> test = new HashMap<>();
		
		// 현재 날짜/시간       
		LocalDateTime now = LocalDateTime.now();         
		// 포맷팅        
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));         
		
		//주문요청 API 요청
		String merchantId = "himedia"; //가맹점 아이디
		String orderNumber = param.get("orderNumber"); //가맹점 아이디 //o_no받아오기
		String amount = param.get("amount"); //amount 받아오기
		String itemName = param.get("itemName"); //p_name 받아오기
		String userName = param.get("userName"); //mem_id 받아오기
		String userAgent = "WP";
		String returnUrl = "returnUrl";
		String signature = "";
		String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String g = "|";
		String apiCertKey = "ac805b30517f4fd08e3e80490e559f8e";
		System.out.println(param);
		
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
		test.put("orderResult",orderResult); // <- 화면으로 데이터보내기
		
		if("0000".equals(orderResult.get("responseCode"))) {
			//정상 JSP
			apiService.deleteCart(param);
			apiService.updateState(param);
		}else {
			//실패 JSP
		}
		
		map.put("test", "test");
		
		return test;
	}
	
	@RequestMapping("/directPay")
	public Map<String, Object> directPay(@RequestParam Map<String, String> param) {
		
		Map<String, Object> test = new HashMap<>();
		
		// 현재 날짜/시간       
		LocalDateTime now = LocalDateTime.now();         
		// 포맷팅        
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));         
		
		apiService.insertOrder(param);
		
		Map<String, Object> getOrder = apiService.getOrder(param);
		
		//주문요청 API 요청
		String merchantId = "himedia"; //가맹점 아이디
		String orderNumber = String.valueOf(getOrder.get("O_NO"));  //가맹점 아이디 //o_no받아오기
		String amount = param.get("amount"); //amount 받아오기
		String itemName = param.get("itemName"); //p_name 받아오기
		String userName = param.get("userName"); //mem_id 받아오기
		String userAgent = "WP";
		String returnUrl = "returnUrl";
		String signature = "";
		String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String g = "|";
		String apiCertKey = "ac805b30517f4fd08e3e80490e559f8e";
		System.out.println(param);
		
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
		test.put("orderResult",orderResult); // <- 화면으로 데이터보내기
		
		map.put("test", "test");
		
		return test;
	}
}
