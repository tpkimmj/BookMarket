package com.book.pay.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.book.pay.dto.PayDTO;

@Mapper
public interface PayDAO {

	void insertOrder(Map<String, String> param);

	Map<String, Object> getOrder(Map<String, String> param);

	void deleteCart(Map<String, String> param);

	void updateState(Map<String, String> param);

	void payResult(Map<String, Object> orderResult);

	void deleteState(Map<String, String> param);

	Map<String, Object> getpay(PayDTO pto);

	void deletePay(Map<String, String> param);

}
