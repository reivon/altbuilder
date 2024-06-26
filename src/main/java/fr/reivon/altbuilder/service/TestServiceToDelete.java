package fr.reivon.altbuilder.service;

import fr.reivon.altbuilder.domain.CardType;
import fr.reivon.altbuilder.domain.Faction;
import fr.reivon.altbuilder.domain.Rarity;
import fr.reivon.altbuilder.domain.card.Card;
import fr.reivon.altbuilder.domain.card.CardSubType;
import fr.reivon.altbuilder.domain.card.Edition;
import fr.reivon.altbuilder.domain.deck.Deck;
import fr.reivon.altbuilder.domain.deck.DeckCard;
import fr.reivon.altbuilder.domain.deck.DeckCardId;
import fr.reivon.altbuilder.domain.user.Customer;
import fr.reivon.altbuilder.repository.CardRepository;
import fr.reivon.altbuilder.repository.DeckRepository;
import fr.reivon.altbuilder.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceToDelete {

    DeckRepository deckRepository;
    CardRepository cardRepository;
    CustomerRepository customerRepository;

    public TestServiceToDelete(DeckRepository deckRepository, CardRepository cardRepository, CustomerRepository customerRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void initDB() {
        CardSubType inge = CardSubType.builder().nameVf("Ingénieur").build();

        Edition btg = Edition.builder().nameVf("BTG").build();

        Card sierra = Card.builder()
                .cardType(CardType.HERO)
                .faction(Faction.AXIOM)
                .alteredId("blabla")
                .editions(List.of(btg))
                .rarity(Rarity.COMMON)
                .nameVf("Sierra").build();

        Card armu = Card.builder()
                .cardType(CardType.CHARACTER)
                .faction(Faction.AXIOM)
                .alteredId("ALT_CORE_B_AX_16_C")
                .editions(List.of(btg))
                .subTypes(List.of(inge))
                .costHand(3)
                .costReserve(3)
                .forestPower(2)
                .mountainPower(2)
                .waterPower(2)
                .rarity(Rarity.COMMON)
                .textVf("{R} Créez un jeton Robot [2/2/2 Scarabot] dans l'Expédition ciblée.")
                .flavorVf("Aucun Scarabot ne survivrait dans le Tumulte sans blindage.")

                .nameVf("Armurière de la Fonderie").build();

        cardRepository.save(sierra);
        cardRepository.save(armu);

        Customer mario = Customer.builder()
                .email("mario@luigi.com")
                .nickname("reivon")
                .password("toto")
                .build();

        mario = customerRepository.save(mario);

        List<Card> cards = cardRepository.getAll();

        Deck deck = Deck.builder()
                .name("Mon 1er deck")
                .author(mario)
                .description("ceci est une description")
                .secret(Boolean.FALSE)
                .build();

        DeckCard deckCard1 = DeckCard.builder()
                .nbCard(1)
                .pk(DeckCardId.builder().card(cards.get(0)).deck(deck).build())
                .build();

        DeckCard deckCard2 = DeckCard.builder()
                .nbCard(2)
                .pk(DeckCardId.builder().card(cards.get(1)).deck(deck).build())
                .build();

        deck.setCards(List.of(deckCard1, deckCard2));

        deckRepository.save(deck);
    }

}
