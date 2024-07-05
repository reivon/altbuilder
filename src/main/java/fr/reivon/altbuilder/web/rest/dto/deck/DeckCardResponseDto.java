package fr.reivon.altbuilder.web.rest.dto.deck;

import fr.reivon.altbuilder.web.rest.dto.card.CardDto;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Jacksonized
public class DeckCardResponseDto {

    CardDto card;
    Integer nbCard;

}
