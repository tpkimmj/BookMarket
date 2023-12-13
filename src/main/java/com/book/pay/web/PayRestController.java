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
		
		Map<String, Object> pay = new HashMap<>();
		
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
		pay.put("orderResult",orderResult); // <- 화면으로 데이터보내기
		
		map.put("pay", "pay");
		
		return pay;
	}
	
	@RequestMapping("/directPay")
	public Map<String, Object> directPay(@RequestParam Map<String, String> param) {
		
		Map<String, Object> pay = new HashMap<>();
		
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
		pay.put("orderResult",orderResult); // <- 화면으로 데이터보내기
		
		map.put("pay", "pay");
		
		return pay;
	}
	
	@RequestMapping("/Canclepay")
	public Map<String, Object> cancel(@RequestParam Map<String, String> param) {
		Map<String, String> map = new HashMap<>();
		Map<String, Object> pay = new HashMap<>();
		String url = "https://api.testpayup.co.kr/v2/api/payment/himedia/cancel2";
		
		//http://localhost:8000/cancel?transactionId=20231129155815ST0423&merchantId=himedia
		//화며에서 데이터 보내고 그 값을 가지고 취소하기
		
		//취소하는거 만들기
    	String merchantId = "himedia";
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
		/* String url = "http://localhost:9004/cancel?"+transactionId+merchantId; */
		Map<String,Object> orderResult = apiService.JsonApi(url, map);
		pay.put("orderResult",orderResult); // <- 화면으로 데이터보내기
		
		if("0000".equals(orderResult.get("responseCode"))) {
			//정상 JSP
			apiService.deleteState(param);
		}else {
			//실패 JSP
		}
		map.put("pay", "pay");
		return pay;
	}
}