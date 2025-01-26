package com.ryan_ribeiro.gamelist_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.ryan_ribeiro.gamelist_api.dto.GameMinRecordDto;
import com.ryan_ribeiro.gamelist_api.dto.GameRecordDto;
import com.ryan_ribeiro.gamelist_api.entities.Game;
import com.ryan_ribeiro.gamelist_api.services.GameService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/games")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public ResponseEntity<List<GameMinRecordDto>> findAllGames() {
		List<GameMinRecordDto> gameList = gameService.findAllGames();
		if(gameList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gameList);
		}
		return ResponseEntity.ok(gameList);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<GameRecordDto> findById(@PathVariable Long id) {
		Optional<GameRecordDto> game = gameService.findById(id);
		if(game.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(game.get());
	}
	
	@GetMapping(value = "/title/{title}")
	public ResponseEntity<Object> findByTitle(@PathVariable String title) {
		var result = gameService.findByTitle(title);
		if(result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No games found with the title '" + title + "'");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
	}
	
	@PostMapping
	public ResponseEntity<Object> saveGame(@RequestBody @Valid GameRecordDto gameDto) {
	    Game game = new Game();
	    BeanUtils.copyProperties(gameDto, game);

	    try {
			gameDto = gameService.saveGame(game);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(gameDto);
	}
}
