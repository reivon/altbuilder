package fr.reivon.altbuilder.web.rest.mapper;

import fr.reivon.altbuilder.domain.deck.Deck;
import fr.reivon.altbuilder.web.rest.dto.DeckDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class DeckMapper {

    public abstract DeckDto deckToDeckDto(Deck deck);
    public abstract List<DeckDto> deckToDeckDto(List<Deck> decks);

    public abstract Deck deckDtoToDeck(DeckDto deckDto);

    @AfterMapping
    void deckDtoToDeck(@MappingTarget Deck deck, DeckDto deckDto) {
        deck.getCards().forEach(it -> it.getPk().setDeck(deck));
    }


}
