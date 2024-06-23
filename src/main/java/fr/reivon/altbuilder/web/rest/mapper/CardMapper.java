package fr.reivon.altbuilder.web.rest.mapper;

import fr.reivon.altbuilder.domain.card.Card;
import fr.reivon.altbuilder.web.rest.dto.CardDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper( CardMapper.class );

    CardDto cardToCardDto(Card card);
    List<CardDto> cardToCardDto(List<Card> card);

}
