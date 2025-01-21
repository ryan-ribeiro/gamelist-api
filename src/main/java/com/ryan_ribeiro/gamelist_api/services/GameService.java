package com.ryan_ribeiro.gamelist_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan_ribeiro.gamelist_api.dto.GameRecordDto;
import com.ryan_ribeiro.gamelist_api.entities.Game;
import com.ryan_ribeiro.gamelist_api.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public List<GameRecordDto> findAllGames() {
		var result = gameRepository.findAll();
		
		List<GameRecordDto> gameDto = result.stream()
				.map(game -> new GameRecordDto(
				game.getId(),
                game.getTitle(),
                game.getYear(),
                game.getImgUrl(),
                game.getShortDescription()
                ))
				.toList();
		
		return gameDto;
	}
	
//	public List<GameRecordDto> findAllGames() {
//		var result = gameRepository.findAll();
//		
//		List<GameRecordDto> gameDto = result.stream()
//				.map(game -> new GameRecordDto(game)) // criação de um novo objeto GameRecordDto para cada game no stream.
//	            .toList();
//		
//		return gameDto;
//	}
}
