package com.book.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.product.dto.ProductDTO;

@Mapper
public interface ProductDAO {

	int getProductCnt();

	List<ProductDTO> getProductList();

	int insertProduct(ProductDTO pdto);

	ProductDTO getProduct(int p_no);


}
