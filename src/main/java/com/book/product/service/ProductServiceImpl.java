package com.book.product.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.book.cart.dto.CartDTO;
import com.book.common.dto.PageDTO;
import com.book.common.dto.RowInterPage;
import com.book.order.dto.OrderDTO;
import com.book.product.dao.ProductDAO;
import com.book.product.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	ProductDAO productDao;

	@Override //전체 상품리스트 불러오기
	public Map<String, Object> getProductList(ProductDTO pdto, PageDTO pageDto) throws Exception {
		// page계산 해서 pageDto
				// list 넣어주고
				// 전체 개수 확인 넣어주고 
				int totCnt = productDao.getProductCnt();
				Map<String, Object> reSet = new HashMap<String, Object>();
				
				if(pageDto.getCurBlock()<=0) pageDto.setCurBlock(1);
				if(pageDto.getCurPage()<=0) pageDto.setCurPage(1);
				
				// 현재 페이지 계산
				int start = (pageDto.getCurPage()-1)*RowInterPage.ROW_OF_PROPAGE + 1;
				int end = (pageDto.getCurPage()*RowInterPage.ROW_OF_PROPAGE)>totCnt ?
						totCnt : pageDto.getCurPage()*RowInterPage.ROW_OF_PROPAGE;
				pdto.setStart(start);
				pdto.setEnd(end);
				List<ProductDTO> pList = productDao.getProductList(pdto);
				// 전체 화면에 나오는 페이지 수
				int pgCnt = (totCnt%RowInterPage.ROW_OF_PROPAGE==0) ?
							totCnt/RowInterPage.ROW_OF_PROPAGE : 
							totCnt/RowInterPage.ROW_OF_PROPAGE+1;
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

	@Override //상품번호에 맞는 상품 가져오기
	public ProductDTO getProduct(int p_no) {
		return productDao.getProduct(p_no);
	}

	@Override //상품삭제
	public int productDel(ProductDTO pdto) {
		return productDao.productDel(pdto);
	}

	@Override
	public Map<String, Object> getProducts(ProductDTO pdto,PageDTO pageDto, String state) {
		Map<String, Object> reSet = new HashMap<String, Object>();
		Map<String, Object> ProductPage = new HashMap<String, Object>();

		if(pageDto.getCurBlock()<=0) pageDto.setCurBlock(1);
		if(pageDto.getCurPage()<=0) pageDto.setCurPage(1);
		
		//상품분류(state) flag(low,high) 저장
		ProductPage.put("state", state);
		ProductPage.put("flag", pdto.getFlag());
		//상품분류(state)에 맞는 상품의 개수 불러오기
		int productTot = productDao.ProductTot(ProductPage);
		// 현재 페이지 계산
		int start = (pageDto.getCurPage()-1)*RowInterPage.ROW_OF_PAGE + 1;
		int end = (pageDto.getCurPage()*RowInterPage.ROW_OF_PAGE)>productTot ?
				productTot : pageDto.getCurPage()*RowInterPage.ROW_OF_PAGE;
		pdto.setStart(start);
		pdto.setEnd(end);

		ProductPage.put("start", start);
		ProductPage.put("end", end);
		
		//상품분류에 맞는 상품들을 불러오고 flag에 맞게 정렬
		if(state=="best") {
			List<ProductDTO> productList = productDao.getBestList(ProductPage);
			reSet.put("productList", productList);
		} else {
			List<ProductDTO> productList = productDao.getProducts(ProductPage);
			reSet.put("productList", productList);
		}
			
		
		int pgCnt = (productTot%RowInterPage.ROW_OF_PAGE==0) ?
				productTot/RowInterPage.ROW_OF_PAGE : 
					productTot/RowInterPage.ROW_OF_PAGE+1;
		int pgBlock = (pgCnt%RowInterPage.PAGE_OF_BLOCK==0) ?
				   pgCnt/RowInterPage.PAGE_OF_BLOCK : pgCnt/RowInterPage.PAGE_OF_BLOCK+1;
		int startPg = (pageDto.getCurBlock()-1)*RowInterPage.PAGE_OF_BLOCK+1;
		int endPg = (pageDto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK > pgCnt) ?
					 pgCnt : pageDto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK;
		pageDto.setPgCnt(pgCnt);
		pageDto.setPgBlock(pgBlock);
		pageDto.setStartPg(startPg);
		pageDto.setEndPg(endPg);
		
		reSet.put("productTot", productTot);
		reSet.put("pageDto", pageDto);
		return reSet;
	}

	@Override
	public Map<String, Object> bookSearch(ProductDTO pdto, PageDTO pageDto) {
		Map<String, Object> reSet = new HashMap<String, Object>();
		Map<String, Object> ProductPage = new HashMap<String, Object>();
		
		if(pageDto.getCurBlock()<=0) pageDto.setCurBlock(1);
		if(pageDto.getCurPage()<=0) pageDto.setCurPage(1);
		//
		ProductPage.put("flag", pdto.getFlag());
		
		//컨트롤러에서 받아온 검색어를 searchText에 저장
		ProductPage.put("searchText", pdto.getSearchText());
		//검색한 책의 개수로 페이지 계산
		int getSearch = productDao.getSearch(ProductPage);

		// 현재 페이지 계산
		int start = (pageDto.getCurPage()-1)*RowInterPage.ROW_OF_PAGE + 1;
		int end = (pageDto.getCurPage()*RowInterPage.ROW_OF_PAGE)>getSearch ?
				getSearch : pageDto.getCurPage()*RowInterPage.ROW_OF_PAGE;
		pdto.setStart(start);
		pdto.setEnd(end);

		ProductPage.put("end", end);
		List<ProductDTO> productList = productDao.bookSearch(pdto);
		
		int pgCnt = (getSearch%RowInterPage.ROW_OF_PAGE==0) ?
				getSearch/RowInterPage.ROW_OF_PAGE : 
					getSearch/RowInterPage.ROW_OF_PAGE+1;
		int pgBlock = (pgCnt%RowInterPage.PAGE_OF_BLOCK==0) ?
				   pgCnt/RowInterPage.PAGE_OF_BLOCK : pgCnt/RowInterPage.PAGE_OF_BLOCK+1;
		int startPg = (pageDto.getCurBlock()-1)*RowInterPage.PAGE_OF_BLOCK+1;
		int endPg = (pageDto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK > pgCnt) ?
					 pgCnt : pageDto.getCurBlock()*RowInterPage.PAGE_OF_BLOCK;
		pageDto.setPgCnt(pgCnt);
		pageDto.setPgBlock(pgBlock);
		pageDto.setStartPg(startPg);
		pageDto.setEndPg(endPg);
		
		reSet.put("getSearch", getSearch);
		reSet.put("productList", productList);
		reSet.put("pageDto", pageDto);
		return reSet;
	}

	@Override
	public List<String> wordSearchSHow(ProductDTO pdto) {
		return productDao.wordSearchSHow(pdto);
	}

	@Override
	public int updateStocks(Map<String, Object> reSet) {
		@SuppressWarnings("unchecked")
		List<CartDTO> cartList = (List<CartDTO>) reSet.get("cartList");
		List<OrderDTO> list = new ArrayList<OrderDTO>(cartList.size());
		for(CartDTO cto : cartList) {
			OrderDTO ovo = new OrderDTO();
			ovo.setP_no(cto.getP_no());
			ovo.setQuantity(cto.getQuantity());
			ovo.setStock(cto.getStock());
			
			list.add(ovo);
		}
		return productDao.updateStocks(list);
	}
	
}

