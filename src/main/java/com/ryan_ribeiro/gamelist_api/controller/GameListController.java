package com.ryan_ribeiro.gamelist_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan_ribeiro.gamelist_api.dto.GameListRecordDto;
import com.ryan_ribeiro.gamelist_api.services.GameListService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
	
	@Autowired
	private GameListService gameListService;
	
	@GetMapping
	public List<GameListRecordDto> findAllGames() {
		List<GameListRecordDto> result = gameListService.findAllGames();
		return result;
	}
}
	