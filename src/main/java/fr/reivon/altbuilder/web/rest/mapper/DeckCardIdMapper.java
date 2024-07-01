package fr.reivon.altbuilder.web.rest.mapper;

import fr.reivon.altbuilder.domain.deck.DeckCardId;
import fr.reivon.altbuilder.web.rest.dto.deck.DeckCardIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface DeckCardIdMapper {

    DeckCardIdMapper INSTANCE = Mappers.getMapper( DeckCardIdMapper.class );

    DeckCardIdDto deckCardIdToDeckCardIdDto(DeckCardId deckCardId);
    List<DeckCardIdDto> deckCardIdToDeckCardIdDto(List<DeckCardId> deckCardId);

}
