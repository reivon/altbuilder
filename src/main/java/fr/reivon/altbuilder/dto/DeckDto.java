package fr.reivon.altbuilder.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DeckDto(Long id, String author, CardDto hero, List<CardDto> characters, List<CardDto> spells,
                      List<CardDto> permanents) {
}