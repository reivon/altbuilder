package fr.reivon.altbuilder.dto;

import lombok.Builder;

@Builder
public record CardDto(String name, Rarity rarity, Integer quantity) {
}
