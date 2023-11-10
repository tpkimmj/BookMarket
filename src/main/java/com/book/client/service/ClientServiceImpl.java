package com.book.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.client.dao.ClientDAO;
import com.book.client.dto.ClientDTO;

@Service
public class ClientServiceImpl implements ClientService{
	@Autowired
	ClientDAO clientDao;

	@Override
	public Map<String, Object> getClients(ClientDTO cdto) {
		Map<String, Object> reSet = new HashMap<>();
		int totCnt = clientDao.getTotalCnt();
		List<ClientDTO> cList = clientDao.getClients(cdto);
		reSet.put("cList", cList);
		reSet.put("totCnt", totCnt);
		
		List<ClientDTO> clientList = null;
		if(cdto.getCno()>0) {
			clientDao.updateReadCnt(cdto);
		}
		reSet.put("clientList", clientList);
		clientList = clientDao.getClients(cdto);
		return reSet;
	}
	@Override
	public ClientDTO getClient(ClientDTO cdto) {
		clientDao.getClients(cdto);
		return clientDao.getClient(cdto);
	}
	
	@Override
	public void generateClient(ClientDTO cdto) {
		clientDao.generateClient(cdto);
	}
	@Override
	public int updateProc(ClientDTO cdto) {
		return clientDao.updateProc(cdto);
	}
	@Override
	public int deleteProc(ClientDTO cdto) {
		return clientDao.deleteProc(cdto);
	}


}
