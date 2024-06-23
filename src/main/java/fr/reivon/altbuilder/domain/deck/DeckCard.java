package fr.reivon.altbuilder.domain.deck;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class DeckCard {

    /** Limit of a card in a deck */
    public static final Integer CARD_LIMIT = 3;

    @EmbeddedId
    DeckCardId pk = new DeckCardId();

    Integer nbCard;

}
