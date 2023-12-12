package com.book.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.chat.dao.ChatDAO;
import com.book.chat.dto.ChatDTO;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	ChatDAO chatDao;
	
	@Override
	public int createChat(ChatDTO chdto) {
		return chatDao.createChat(chdto);
	}

	@Override
	public List<Map<String, Object>> getChat(ChatDTO chdto) {
		List<Map<String, Object>> chatLog = chatDao.getChat(chdto);
		return chatLog;
	}

	@Override
	public int deleteChat(ChatDTO chdto) {
		return chatDao.deleteChat(chdto);
	}

	@Override
	public List<Map<String, Object>> getAdminChat(ChatDTO chdto) {
		List<Map<String, Object>> chatLog = chatDao.getAdminChat(chdto);
		return chatLog;
	}

	@Override
	public int deleteAdminChat(ChatDTO chdto) {
		return chatDao.deleteAdminChat(chdto);
	}


}
