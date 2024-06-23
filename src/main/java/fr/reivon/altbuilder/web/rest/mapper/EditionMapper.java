package fr.reivon.altbuilder.web.rest.mapper;

import fr.reivon.altbuilder.domain.card.Card;
import fr.reivon.altbuilder.domain.card.Edition;
import fr.reivon.altbuilder.web.rest.dto.EditionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EditionMapper {

    EditionMapper INSTANCE = Mappers.getMapper( EditionMapper.class );

    EditionDto EditionToEditionDto(Edition edition);
    List<EditionDto> EditionToEditionDto(List<Edition> edition);

}
