package com.ryan_ribeiro.gamelist_api.dto;

import com.ryan_ribeiro.gamelist_api.entities.GameList;

public record GameListRecordDto (
		Long id,
		String name) {
	
	public GameListRecordDto(GameList list) {
		this(list.getId(), list.getName());
	}
}
