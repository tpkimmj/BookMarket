package com.book.pay.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayDAO {

	void insertOrder(Map<String, String> param);

	Map<String, Object> getOrder(Map<String, String> param);

	void deleteCart(Map<String, String> param);

	void updateState(Map<String, String> param);

}
