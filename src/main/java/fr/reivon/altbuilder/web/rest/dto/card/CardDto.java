package fr.reivon.altbuilder.web.rest.dto.card;

import fr.reivon.altbuilder.domain.CardType;
import fr.reivon.altbuilder.domain.Faction;
import fr.reivon.altbuilder.domain.Rarity;
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
public class CardDto {

    String id;
    Faction faction;
    Rarity rarity;
    CardType cardType;
    @Builder.Default
    List<CardSubTypeDto> subTypes = new ArrayList<>();
    @Builder.Default
    List<EditionDto> editions = new ArrayList<>();
    Integer costHand;
    Integer costReserve;
    String nameVo;
    String nameVf;
    String textVo;
    String textVf;
    String flavorVo;
    String flavorVf;
    Integer forestPower;
    Integer mountainPower;
    Integer waterPower;

}
