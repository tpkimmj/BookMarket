package com.book.client.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.client.dto.ClientDTO;


@Mapper
public interface ClientDAO {

	int getTotalCnt();
	
	int updateReadCnt(ClientDTO cdto);
	
	void generateClient(ClientDTO cdto);
	
	int insertProc(ClientDTO cdto);

	int deleteProc(ClientDTO cdto);

	int updateProc(ClientDTO cdto);

	List<ClientDTO> getClients(ClientDTO cdto);

	ClientDTO getClient(ClientDTO cdto);

}
