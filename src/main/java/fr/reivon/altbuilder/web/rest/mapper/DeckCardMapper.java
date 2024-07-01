package fr.reivon.altbuilder.web.rest.mapper;

import fr.reivon.altbuilder.domain.deck.DeckCard;
import fr.reivon.altbuilder.web.rest.dto.deck.DeckCardDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface DeckCardMapper {

    DeckCardMapper INSTANCE = Mappers.getMapper( DeckCardMapper.class );

    DeckCardDto deckCardToDeckCardDto(DeckCard deckCard);
    List<DeckCardDto> deckCardToDeckCardDto(List<DeckCard> deckCards);

}
