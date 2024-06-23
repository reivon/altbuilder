package fr.reivon.altbuilder.domain.deck;

import fr.reivon.altbuilder.domain.card.Card;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class DeckCardId {

    @ManyToOne(fetch = FetchType.LAZY)
    Deck deck;

    @ManyToOne
    Card card;

}
