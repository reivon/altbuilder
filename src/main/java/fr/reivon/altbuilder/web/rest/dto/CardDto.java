package fr.reivon.altbuilder.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.reivon.altbuilder.domain.CardType;
import fr.reivon.altbuilder.domain.Faction;
import fr.reivon.altbuilder.domain.Rarity;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Jacksonized
public class CardDto {

    Long id;
    String alteredId;
    Faction faction;
    Rarity rarity;
    CardType cardType;
    List<CardSubTypeDto> subTypes;
    List<EditionDto> editions;
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
