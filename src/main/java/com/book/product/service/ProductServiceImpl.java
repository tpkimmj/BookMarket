package com.book.product.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.book.common.dto.PageDTO;
import com.book.common.dto.RowInterPage;
import com.book.product.dao.ProductDAO;
import com.book.product.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductDAO productDao;

	@Override
	public Map<String, Object> getProductList(ProductDTO pdto, PageDTO pageDto) throws Exception {
		// page계산 해서 pageDto
				// list 넣어주고
				// 전체 개수 확인 넣어주고 
				int totCnt = productDao.getProductCnt();
				Map<String, Object> reSet = new HashMap<String, Object>();
				
				if(pageDto.getCurBlock()<=0) pageDto.setCurBlock(1);
				if(pageDto.getCurPage()<=0) pageDto.setCurPage(1);
				
				// 현재 페이지 계산
				int start = (pageDto.getCurPage()-1)*RowInterPage.ROW_OF_PAGE + 1;
				int end = (pageDto.getCurPage()*RowInterPage.ROW_OF_PAGE)>totCnt ?
						totCnt : pageDto.getCurPage()*RowInterPage.ROW_OF_PAGE;
				pdto.setStart(start);
				pdto.setEnd(end);
				List<ProductDTO> pList = productDao.getProductList(pdto);
				// 전체 화면에 나오는 페이지 수
				int pgCnt = (totCnt%RowInterPage.ROW_OF_PAGE==0) ?
							totCnt/RowInterPage.ROW_OF_PAGE : 
							totCnt/RowInterPage.ROW_OF_PAGE+1;
				int pgBlock = (pgCnt%RowInterPage.PAGE_OF_BLOCK==0) ?
						   pgCnt/RowInterPage.PAGE_OF_BLOCK : pgCnt/RowInterPage.PAGE_OF_BLOCK+1;
				int startPg = (pageDto.getCurBlock()-1)*RowInterPage.PAGE_OF_BLOCK+1;
				int endPg = (pageDto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK > pgCnt) ?
							 pgCnt : pageDto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK;
				pageDto.setPgCnt(pgCnt);
				pageDto.setPgBlock(pgBlock);
				pageDto.setStartPg(startPg);
				pageDto.setEndPg(endPg);
				
				reSet.put("pList", pList);
				reSet.put("totCnt", totCnt);
				reSet.put("pageDto", pageDto);
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
		String sourceFileName = file.getOriginalFilename();
		File destinetionFile;
		if(sourceFileName==null || sourceFileName.length()==0) {
			if(pdto.getImage()==null || pdto.getImage().equals("ready.gif")) 
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
		return productDao.updateProduct(pdto);
	}

	@Override
	public ProductDTO getProduct(int p_no) {
		return productDao.getProduct(p_no);
	}

	@Override
	public int productDel(ProductDTO pdto) {
		return productDao.productDel(pdto);
	}

}
