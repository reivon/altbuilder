package fr.reivon.altbuilder.web.rest.mapper.deck;

import fr.reivon.altbuilder.domain.deck.Deck;
import fr.reivon.altbuilder.web.rest.dto.deck.DeckCardResponseDto;
import fr.reivon.altbuilder.web.rest.dto.deck.DeckDto;
import fr.reivon.altbuilder.web.rest.dto.deck.DeckResponseDto;
import fr.reivon.altbuilder.web.rest.mapper.CardMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Mapper(componentModel = "spring")
public abstract class DeckResponseMapper {

    @Autowired
    CardMapper cardMapper;

    @Mapping(source = "author.nickname", target = "author")
    @Mapping(ignore = true, target= "cards")
    public abstract DeckResponseDto deckToDeckResponseDto(Deck deck);

    @AfterMapping
    void setCards(@MappingTarget DeckResponseDto target, Deck source) {
        source.getCards().forEach(card -> {
            DeckCardResponseDto cardDto = new DeckCardResponseDto();
            cardDto.setNbCard(card.getNbCard());
            cardDto.setCard(cardMapper.cardToCardDto(card.getPk().getCard()));
            target.getCards().add(cardDto);
        });
    }

}
