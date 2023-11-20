package com.book.product.service;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.book.common.dto.PageDTO;
import com.book.order.dto.OrderDTO;
import com.book.product.dto.ProductDTO;

public interface ProductService {


	int insertProduct(ProductDTO pdto, MultipartFile file) throws Exception;

	int updateProduct(ProductDTO pdto, MultipartFile file) throws Exception;

	ProductDTO getProduct(int p_no);

	int productDel(ProductDTO pdto);

	Map<String, Object> getProductList(ProductDTO pdto, PageDTO pageDto) throws Exception;

	Map<String, Object> getProducts(ProductDTO pdto, PageDTO pageDto, String state);
	
	Map<String, Object> bookSearch(ProductDTO pdto, PageDTO pageDto);

	void updateStocks(Hashtable<Integer, OrderDTO> hCartList);

}
