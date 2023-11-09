package com.book.client.service;

import java.util.Map;

import com.book.client.dto.ClientDTO;

public interface ClientService {

	public void generateClient(ClientDTO cdto);
//    public Map<String, Object> getClients(ClientDTO cdto);
	public int updateProc(ClientDTO cdto);
	public int deleteProc(ClientDTO cdto);
	public Map<String, Object> getClients(ClientDTO cdto);
	public ClientDTO getClient(ClientDTO cdto);
}
