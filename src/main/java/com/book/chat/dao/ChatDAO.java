package com.book.chat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.book.chat.dto.ChatDTO;

@Mapper
public interface ChatDAO {

	int createChat(ChatDTO chdto);

	List<Map<String, Object>> getChat(ChatDTO chdto);

	int deleteChat(ChatDTO chdto);

	List<Map<String, Object>> getAdminChat(ChatDTO chdto);

	int deleteAdminChat(ChatDTO chdto);

}
