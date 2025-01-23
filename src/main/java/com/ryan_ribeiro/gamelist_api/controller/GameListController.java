package com.ryan_ribeiro.gamelist_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan_ribeiro.gamelist_api.dto.GameListRecordDto;
import com.ryan_ribeiro.gamelist_api.dto.GameMinRecordDto;
import com.ryan_ribeiro.gamelist_api.dto.ReplacementRecordDto;
import com.ryan_ribeiro.gamelist_api.services.GameListService;
import com.ryan_ribeiro.gamelist_api.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
	
	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public List<GameListRecordDto> findAllGames() {
		List<GameListRecordDto> result = gameListService.findAllGames();
		return result;
	}
	
	@GetMapping(value = "/{listId}/games")
	public List<GameMinRecordDto> findByList(@PathVariable Long listId) {
		List<GameMinRecordDto> result = gameService.findByList(listId);
		return result;
	}
	
	@PostMapping(value = "/{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementRecordDto body) {
		gameListService.move(listId, body.sourceIndex(), body.destinationIndex());
	}
	
}
	