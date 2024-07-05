package fr.reivon.altbuilder.web.rest.dto.deck;

import fr.reivon.altbuilder.web.rest.dto.customer.CustomerDto;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Jacksonized
public class DeckResponseDto {

    Long id;
    String name;
    Boolean secret;
    String description;
    String author;

    @Builder.Default
    List<DeckCardResponseDto> cards = new ArrayList<>();
}