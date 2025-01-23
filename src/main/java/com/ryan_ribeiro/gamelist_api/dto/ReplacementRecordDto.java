package com.ryan_ribeiro.gamelist_api.dto;

import jakarta.validation.constraints.NotNull;

public record ReplacementRecordDto (
		@NotNull int sourceIndex,
		@NotNull int destinationIndex) 
		{

}
