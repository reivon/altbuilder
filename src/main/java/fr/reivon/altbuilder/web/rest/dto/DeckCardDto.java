package fr.reivon.altbuilder.web.rest.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Jacksonized
public class DeckCardDto {

    DeckCardIdDto pk = new DeckCardIdDto();

    Integer nbCard;

}
