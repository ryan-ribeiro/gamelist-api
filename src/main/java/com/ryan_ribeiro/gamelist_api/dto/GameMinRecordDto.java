package com.ryan_ribeiro.gamelist_api.dto;

import com.ryan_ribeiro.gamelist_api.entities.Game;
import com.ryan_ribeiro.gamelist_api.projections.GameMinProjection;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record GameMinRecordDto (
	Long id,
	@NotBlank String title,
	@NotBlank Integer year,
	@NotBlank String imgUrl,
	@NotBlank String shortDescription){
	
	// Construtor que recebe um Game e cria um GameRecordDto a partir dele
    public GameMinRecordDto(Game game) {
        this(game.getId(), game.getTitle(), game.getYear(), game.getImgUrl(), game.getShortDescription());
    }
    
 // Construtor que recebe um Projection e cria um GameRecordDto a partir dele
    public GameMinRecordDto(GameMinProjection projection) {
        this(projection.getId(), projection.getTitle(), projection.getYear(), projection.getImgUrl(), projection.getShortDescription());
    }
}
