package com.ryan_ribeiro.gamelist_api.dto;

import org.springframework.beans.BeanUtils;

import com.ryan_ribeiro.gamelist_api.entities.Game;

public record GameRecordDto(
	Long id,
	String title,
	Integer year,
	String genre,
	String platforms,
	Double score,
	String imgUrl,
	String shortDescription,
	String longDescription) {

	public GameRecordDto(Game game) {
		this(game.getId(), game.getTitle(), game.getYear(), game.getGenre(),
			 game.getPlatforms(), game.getScore(), game.getImgUrl(),
			 game.getShortDescription(), game.getLongDescription());
	}
	
}
