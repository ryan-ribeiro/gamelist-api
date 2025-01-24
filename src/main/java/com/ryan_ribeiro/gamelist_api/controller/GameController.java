package com.ryan_ribeiro.gamelist_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan_ribeiro.gamelist_api.dto.GameMinRecordDto;
import com.ryan_ribeiro.gamelist_api.dto.GameRecordDto;
import com.ryan_ribeiro.gamelist_api.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public List<GameMinRecordDto> findAllGames() {
		List<GameMinRecordDto> result = gameService.findAllGames();
		return result;
	}
	
	@GetMapping(value = "/{id}")
	public GameRecordDto findById(@PathVariable Long id) {
		GameRecordDto result = gameService.findById(id);
		return result;
	}
	
	@GetMapping(value = "/title/{title}")
	public List<GameMinRecordDto> findById(@PathVariable String title) {
		var result = gameService.findByTitle(title);
		return result;
	}
}
