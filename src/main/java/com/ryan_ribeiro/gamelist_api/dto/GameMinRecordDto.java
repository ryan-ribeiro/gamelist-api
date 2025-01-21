package com.ryan_ribeiro.gamelist_api.dto;

import com.ryan_ribeiro.gamelist_api.entities.Game;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record GameMinRecordDto (
	@Valid Long id,
	@Valid @NotBlank String title,
	@Valid @NotBlank Integer year,
	@Valid @NotBlank String imgUrl,
	@Valid @NotBlank String shortDescription){
	
	// Construtor que recebe um Game e cria um GameRecordDto a partir dele
    public GameMinRecordDto(Game game) {
        this(game.getId(), game.getTitle(), game.getYear(), game.getImgUrl(), game.getShortDescription());
    }
}
