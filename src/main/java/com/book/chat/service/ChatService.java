package com.book.chat.service;

import java.util.List;
import java.util.Map;

import com.book.chat.dto.ChatDTO;

public interface ChatService {

	int createChat(ChatDTO chdto);

	List<Map<String, Object>> getChat(ChatDTO chdto);

	int deleteChat(ChatDTO chdto);

	List<Map<String, Object>> getAdminChat(ChatDTO chdto);

	int deleteAdminChat(ChatDTO chdto);


}
