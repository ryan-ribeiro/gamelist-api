package com.ryan_ribeiro.gamelist_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryan_ribeiro.gamelist_api.dto.GameListRecordDto;
import com.ryan_ribeiro.gamelist_api.entities.GameList;
import com.ryan_ribeiro.gamelist_api.repositories.GameListRepository;

@Service
public class GameListService {
	@Autowired
	private GameListRepository gameListRepository;
	
	@Transactional(readOnly = true)
	public List<GameListRecordDto> findAllGames() {
		List<GameList> result = gameListRepository.findAll();
		
		List<GameListRecordDto> gameDto = result.stream()
				.map(game -> new GameListRecordDto(
				game.getId(),
                game.getName()
                ))
				.toList();
		
		return gameDto;
	}
}
