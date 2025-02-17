package com.ryan_ribeiro.gamelist_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryan_ribeiro.gamelist_api.dto.GameMinRecordDto;
import com.ryan_ribeiro.gamelist_api.dto.GameRecordDto;
import com.ryan_ribeiro.gamelist_api.entities.Game;
import com.ryan_ribeiro.gamelist_api.projections.GameMinProjection;
import com.ryan_ribeiro.gamelist_api.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameMinRecordDto> findAllGames() {
		var result = gameRepository.findAll();
		
		List<GameMinRecordDto> gameDto = result.stream()
				.map(game -> new GameMinRecordDto(
				game.getId(),
                game.getTitle(),
                game.getYear(),
                game.getImgUrl(),
                game.getShortDescription()
                ))
				.toList();
		
		return gameDto;
	}
	
//	@Transactional(readOnly = true)
//	public List<GameRecordDto> findAllGames() {
//		var result = gameRepository.findAll();
//		
//		List<GameRecordDto> gameDto = result.stream()
//				.map(game -> new GameRecordDto(game)) // criação de um novo objeto GameRecordDto para cada game no stream.
//	            .toList();
//		
//		return gameDto;
//	}
	
	@Transactional(readOnly = true)
	public Optional<GameRecordDto> findById(Long id) {
		return Optional.ofNullable(findById(id).orElse(null));
	}
	
	@Transactional(readOnly = true)
	public List<GameMinRecordDto> findByList(Long listId) {
		List<GameMinProjection> result = gameRepository.searchByList(listId);
		
		List<GameMinRecordDto> gameDto = result.stream()
				.map(game -> new GameMinRecordDto(
				game.getId(),
                game.getTitle(),
                game.getYear(),
                game.getImgUrl(),
                game.getShortDescription()
                ))
				.toList();
		
		return gameDto;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinRecordDto> findByTitle(String title) {
		List<GameMinProjection> list = gameRepository.searchByTitle(title);
		
		List<GameMinRecordDto> gameDto = list.stream()
				.map(game -> new GameMinRecordDto(
				game.getId(),
                game.getTitle(),
                game.getYear(),
                game.getImgUrl(),
                game.getShortDescription()
                ))
				.toList();
		
		return gameDto;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinRecordDto> searchByScore(Double minScore, Double maxScore) {
		List<Game> list = gameRepository.findByScoreBetween(minScore, maxScore);
		
		List<GameMinRecordDto> gameDto = list.stream()
				.map(game -> new GameMinRecordDto(
				game.getId(),
                game.getTitle(),
                game.getYear(),
                game.getImgUrl(),
                game.getShortDescription()
                ))
				.toList();
		
		return gameDto;
	}
	
	@Transactional(readOnly = true)
	public GameRecordDto saveGame(Game game) throws Exception {
		if(game.getId() != null) {
			Game jogoExistente = gameRepository.findById(game.getId()).orElse(null);
			
			if(jogoExistente != null) {
				throw new Exception("Jogo já cadastrado com o ID fornecido");
			}
		}
		Game savedGame = gameRepository.save(game);
		return new GameRecordDto(savedGame);
	}
}
