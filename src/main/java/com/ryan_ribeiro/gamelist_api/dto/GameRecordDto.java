package com.ryan_ribeiro.gamelist_api.dto;

import jakarta.validation.Valid;

public record GameRecordDto (
	@Valid Long id,
	@Valid String title,
	@Valid Integer year,
	@Valid String imgUrl,
	@Valid String shortDescription){
	
	
	
}
