package com.book.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.book.product.dto.ProductDTO;

@Mapper
public interface ProductDAO {

	int getProductCnt();
	
	int ProductTot(Map<String, Object> productPage);

	List<ProductDTO> getProductList(ProductDTO pdto);

	int insertProduct(ProductDTO pdto);

	ProductDTO getProduct(int p_no);

	int updateProduct(ProductDTO pdto);

	int productDel(ProductDTO pdto);

	int getTotalCnt();

	List<ProductDTO> getProducts(Map<String, Object> productPage);

	List<ProductDTO> bookSearch(ProductDTO pdto);

}
