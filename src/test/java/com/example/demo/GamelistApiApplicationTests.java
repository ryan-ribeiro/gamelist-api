package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ryan_ribeiro.gamelist_api.projections.GameMinProjection;
import com.ryan_ribeiro.gamelist_api.repositories.GameRepository;

@SpringBootTest
class GamelistApiApplicationTests {
	
	@Autowired
	GameRepository repository;

	@Test
	public void testSearchByScore() {
	    List<GameMinProjection> results = repository.searchByScore(3.0, 5.0);
	    assertFalse(results.isEmpty());
	    results.forEach(game -> {
	        System.out.println(game.getTitle());
	    });
	}


}
