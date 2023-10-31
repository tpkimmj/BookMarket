package com.book.product.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.book.common.dto.PageDTO;
import com.book.product.dto.ProductDTO;

public interface ProductService {

	Map<String, Object> getProductList(PageDTO pageDto) throws Exception;

	int insertProduct(ProductDTO pdto, MultipartFile file) throws Exception;

	int updateProduct(ProductDTO pdto, MultipartFile file) throws Exception;

	ProductDTO getProduct(int p_no);

	int productDel(ProductDTO pdto);

}
