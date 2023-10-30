package com.book.product.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.book.common.dto.PageDTO;
import com.book.product.dao.ProductDAO;
import com.book.product.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDAO productDao;

	@Override
	public Map<String, Object> getProductList(PageDTO pageDto) throws Exception {
		// page계산 해서 pageDto
				// list 넣어주고
				// 전체 개수 확인 넣어주고 
				int cnt = productDao.getProductCnt();
				Map<String, Object> reSet = new HashMap<String, Object>();
				List<ProductDTO> pList = productDao.getProductList();
				
				reSet.put("pcnt", cnt);
				reSet.put("pList", pList);
				return reSet;
	}

	@Override
	public int insertProduct(ProductDTO pdto, MultipartFile file) throws Exception {
		String sourceFileName = file.getOriginalFilename();
		File destinetionFile;
		if(sourceFileName==null || sourceFileName.length()==0) {
			pdto.setImage("ready.gif");
		} else {
			pdto.setImage(sourceFileName);
			destinetionFile = new File(pdto.getPath()+sourceFileName);
			destinetionFile.getParentFile().mkdirs(); // 파일명으로 생성
			try {
				// 받은 파일 전송
				file.transferTo(destinetionFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return productDao.insertProduct(pdto);
	}

	@Override
	public int updateProduct(ProductDTO pdto, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
