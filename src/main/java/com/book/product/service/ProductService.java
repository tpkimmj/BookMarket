package com.book.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.book.common.dto.PageDTO;
import com.book.product.dto.ProductDTO;

public interface ProductService {


	int insertProduct(ProductDTO pdto, MultipartFile file) throws Exception;

	int updateProduct(ProductDTO pdto, MultipartFile file) throws Exception;

	ProductDTO getProduct(int p_no);

	int productDel(ProductDTO pdto);

	Map<String, Object> getProductList(ProductDTO pdto, PageDTO pageDto) throws Exception;

	Map<String, Object> getProducts(ProductDTO pdto, PageDTO pageDto, String state);
	
	Map<String, Object> bookSearch(ProductDTO pdto, PageDTO pageDto);

	List<String> wordSearchSHow(ProductDTO pdto);

	int updateStocks(Map<String, Object> reSet);

}
