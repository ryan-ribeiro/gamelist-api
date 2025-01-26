package com.ryan_ribeiro.gamelist_api.dto;

import com.ryan_ribeiro.gamelist_api.entities.Game;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GameRecordDto(
    Long id,
    @NotBlank String title,
    @NotNull
    @Min(value = 1900, message = "Year must be at least 1900")
    @Max(value = 2100, message = "Year must be no more than 2100")
    Integer year,
    @NotBlank String genre,
    @NotBlank String platforms,
    @NotNull
    @Min(value = 0, message = "Nota mínima: zero")
    @Max(value = 5, message = "Nota máxima: cinco")
    Double score,
    @NotBlank String imgUrl,
    @NotBlank String shortDescription,
    @NotBlank String longDescription) {

    public GameRecordDto(Game game) {
        this(game.getId(), game.getTitle(), game.getYear(), game.getGenre(),
             game.getPlatforms(), game.getScore(), game.getImgUrl(),
             game.getShortDescription(), game.getLongDescription());
    }
}
