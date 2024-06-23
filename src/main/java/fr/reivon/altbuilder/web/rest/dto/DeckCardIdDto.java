package fr.reivon.altbuilder.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Jacksonized
public class DeckCardIdDto {

    @JsonIgnoreProperties
    DeckDto deck;

    CardDto card;

}
