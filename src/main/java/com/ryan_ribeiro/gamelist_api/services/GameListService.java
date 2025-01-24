package com.ryan_ribeiro.gamelist_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryan_ribeiro.gamelist_api.dto.GameListRecordDto;
import com.ryan_ribeiro.gamelist_api.entities.GameList;
import com.ryan_ribeiro.gamelist_api.projections.GameMinProjection;
import com.ryan_ribeiro.gamelist_api.repositories.GameListRepository;
import com.ryan_ribeiro.gamelist_api.repositories.GameRepository;

@Service
public class GameListService {
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
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
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for(int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
	
}
