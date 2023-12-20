package com.book.pay.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.pay.dao.PayDAO;
import com.book.pay.dto.PayDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class ApiService {
	
	@Autowired
	private PayDAO payDao;
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> JsonApi(String url, Map<String,String> map) {
		
		
		Map<String,Object> returnMap = new HashMap<>();
		
		//api 통신 서비스 만들기
		//http 통신
		
		//1. 자바 기본 라이브러리 만들수있다. (X)
		// 쉽게 만들 예정 ( 외부 라이브러리 사용 하여)
		// okhttp라는 라이브러리를 사용할예정.
		
		OkHttpClient client = new OkHttpClient();

        // JSON 데이터를 담은 문자열
		
		//Map > JSON string 으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = "";
		try {
			
			jsonBody = objectMapper.writeValueAsString(map);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


        // JSON 헤더 설정
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(jsonBody, JSON);

        // POST 요청 생성
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            // 동기적으로 POST 요청 보내기
            Response response = client.newCall(request).execute();

            returnMap = objectMapper.readValue(response.body().string(), Map.class);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        
        return returnMap;
	}
	
	
	public String getSHA256Hash(String input) throws NoSuchAlgorithmException {
        // SHA-256 해시 함수 생성
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // 입력 문자열을 바이트 배열로 변환
        byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

        // 바이트 배열을 16진수 문자열로 변환
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : bytes) {
            String hex = String.format("%02x", b);
            hexStringBuilder.append(hex);
        }

        return hexStringBuilder.toString();
    }


	public void insertOrder(Map<String, String> param) {
		payDao.insertOrder(param);
	}


	public Map<String, Object> getOrder(Map<String, String> param) {
		return payDao.getOrder(param);
	}


	public void deleteCart(Map<String, String> param) {
		payDao.deleteCart(param);
	}


	public void updateState(Map<String, String> param) {
		payDao.updateState(param);
	}


	public void payResult(Map<String, Object> orderResult) {
		payDao.payResult(orderResult);
	}
	
	public void deleteState(Map<String, String> param) {
		payDao.deleteState(param);
		payDao.deletePay(param);
	}

	public Map<String, Object> getpay(PayDTO pto) {
		return payDao.getpay(pto);
	}
}
