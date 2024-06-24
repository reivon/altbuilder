package fr.reivon.altbuilder.web.rest.mapper;

import fr.reivon.altbuilder.domain.deck.Deck;
import fr.reivon.altbuilder.web.rest.dto.DeckDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface DeckMapper {

    DeckMapper INSTANCE = Mappers.getMapper( DeckMapper.class );

    DeckDto deckToDeckDto(Deck deck);
    List<DeckDto> deckToDeckDto(List<Deck> decks);

    Deck deckDtoToDeck(DeckDto deckDto);
}
