package com.ryan_ribeiro.gamelist_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryan_ribeiro.gamelist_api.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{

}
