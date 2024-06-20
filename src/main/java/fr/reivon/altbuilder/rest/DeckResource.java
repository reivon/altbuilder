package fr.reivon.altbuilder.rest;

import fr.reivon.altbuilder.dto.CardDto;
import fr.reivon.altbuilder.dto.DeckDto;

import fr.reivon.altbuilder.dto.Rarity;
import lombok.Builder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DeckResource {


    @GetMapping("deck")
    public DeckDto getDeck() {
        return DeckDto.builder().id(1L).author("Reivon")
                .hero(CardDto.builder().name("Treyst & rossum").quantity(1).rarity(Rarity.COMMON).build())
                .characters(List.of(
                        CardDto.builder().name("Élémentaire de Kélon").quantity(3).rarity(Rarity.COMMON).build(),
                        CardDto.builder().name("Ingénieure de la Fonderie").quantity(3).rarity(Rarity.COMMON).build(),
                        CardDto.builder().name("Mécanicienne de la Fonderie").quantity(3).rarity(Rarity.RARE).build(),
                        CardDto.builder().name("Récupérateur Axiom").quantity(2).rarity(Rarity.COMMON).build(),
                        CardDto.builder().name("Récupérateur Axiom").quantity(1).rarity(Rarity.RARE).build()
                ))
                .spells(List.of(
                        CardDto.builder().name("Sceaux Adhésifs").quantity(3).rarity(Rarity.RARE).build()
                ))
                .permanents(List.of(
                        CardDto.builder().name("Havre, Bastion Bravos").quantity(3).rarity(Rarity.RARE).build()
                ))
                .build();
    }


}
