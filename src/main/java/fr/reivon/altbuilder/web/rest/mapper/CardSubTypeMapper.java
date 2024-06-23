package fr.reivon.altbuilder.web.rest.mapper;

import fr.reivon.altbuilder.domain.card.CardSubType;
import fr.reivon.altbuilder.web.rest.dto.CardSubTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardSubTypeMapper {

    CardSubTypeMapper INSTANCE = Mappers.getMapper(CardSubTypeMapper.class);

    CardSubTypeDto CardSubTypeToCardSubTypeDto(CardSubType cardSubType);
    List<CardSubTypeDto> CardSubTypeToCardSubTypeDto(List<CardSubType> cardSubTypes);

}
