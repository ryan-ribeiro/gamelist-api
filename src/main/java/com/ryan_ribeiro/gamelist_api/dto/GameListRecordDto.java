package com.ryan_ribeiro.gamelist_api.dto;

import com.ryan_ribeiro.gamelist_api.entities.GameList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GameListRecordDto (
		@Valid Long id,
		@NotNull @NotBlank String name) {
	
	public GameListRecordDto(GameList list) {
		this(list.getId(), list.getName());
	}
}
