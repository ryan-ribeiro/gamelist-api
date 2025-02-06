package com.ryan_ribeiro.gamelist_api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ryan_ribeiro.gamelist_api.entities.Game;
import com.ryan_ribeiro.gamelist_api.projections.GameMinProjection;

public interface GameRepository extends JpaRepository<Game, Long>{
	@Query(nativeQuery = true, value = """
		    SELECT 
		        tb_game.id, 
		        tb_game.title, 
		        tb_game.game_year AS "year", 
		        tb_game.img_url AS imgUrl, 
		        tb_game.short_description AS shortDescription, 
		        tb_belonging.position
		    FROM tb_game
		    INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
		    WHERE tb_belonging.list_id =:listId
		    ORDER BY tb_belonging.position
		    """)

	List<GameMinProjection> searchByList(Long listId);
	
	@Query(nativeQuery = true, value = """
			SELECT
			    tb_game.id,
			    tb_game.title,
			    tb_game.game_year AS "year",
			    tb_game.img_url AS imgUrl,
			    tb_game.short_description AS shortDescription,
			    tb_belonging.position
			FROM
			    tb_game
			LEFT JOIN
			    tb_belonging ON tb_game.id = tb_belonging.game_id
			WHERE
			    LOWER(tb_game.title) LIKE LOWER(:title || '%')
			ORDER BY
			    tb_game.score;
	        """)
	List<GameMinProjection> searchByTitle(@Param("title") String title);
	
	// JPA Query Method CONTAINING e IGNORECASE, equivalente à consulta acima
//	List<Game> findByTitleContainingIgnoreCase(String title);
	
//	@Query("SELECT g FROM Game g WHERE g.score >= :minScore AND g.score <= :maxScore ORDER BY g.score DESC")
//	List<GameMinProjection> searchByScore(@Param("minScore") Double minScore, @Param("maxScore") Double maxScore);
	
	// JPA Query Method BETWEEN; Perceba que é necessário retornar uma Lista de entidade, e não de Dto de Entidade
	// É equivalente à consulta acima.
	List<Game> findByScoreBetween(Double minScore, Double maxScore);
}
	