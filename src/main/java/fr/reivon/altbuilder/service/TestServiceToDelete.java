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
        CardSubType subTypeInge = CardSubType.builder().nameVf("Ingénieur").build();
        CardSubType subTypeManeuver = CardSubType.builder().nameVf("Maneuver").build();

        Edition btg = Edition.builder().nameVf("BTG").build();

        Card sierra = Card.builder()
                .cardType(CardType.HERO)
                .faction(Faction.AXIOM)
                .id("ALT_CORE_B_AX_01_C")
                .editions(List.of(btg))
                .rarity(Rarity.COMMON)
                .nameVf("Sierra & Oddball")
                .textVf("When you play a Permanent with Hand Cost {3} or more — You may exhaust me ({T}) to create a [Brassbug 2/2/2] Robot token in target Expedition.")
                .build();

        Card armu = Card.builder()
                .cardType(CardType.CHARACTER)
                .faction(Faction.AXIOM)
                .id("ALT_CORE_B_AX_16_R1")
                .editions(List.of(btg))
                .subTypes(List.of(subTypeInge))
                .costHand(3)
                .costReserve(3)
                .forestPower(2)
                .mountainPower(2)
                .waterPower(2)
                .rarity(Rarity.RARE)
                .textVf("{R} Créez un jeton Robot [2/2/2 Scarabot] dans l'Expédition ciblée.")
                .flavorVf("Aucun Scarabot ne survivrait dans le Tumulte sans blindage.")
                .nameVf("Armurière de la Fonderie").build();

        Card inge = Card.builder()
                .cardType(CardType.CHARACTER)
                .faction(Faction.AXIOM)
                .id("ALT_CORE_B_AX_05_R1")
                .editions(List.of(btg))
                .subTypes(List.of(subTypeInge))
                .costHand(2)
                .costReserve(2)
                .forestPower(2)
                .mountainPower(2)
                .waterPower(1)
                .rarity(Rarity.RARE)
                .textVf("{R} The next Permanent you play this Afternoon costs {2} less.")
                .flavorVf("Behind every Axiom masterwork, there are Foundry Engineers.")
                .nameVf("Ingénieure de la Fonderie").build();

        Card meca = Card.builder()
                .cardType(CardType.CHARACTER)
                .faction(Faction.AXIOM)
                .id("ALT_CORE_B_AX_07_C")
                .editions(List.of(btg))
                .subTypes(List.of(subTypeInge))
                .costHand(1)
                .costReserve(1)
                .forestPower(0)
                .mountainPower(1)
                .waterPower(1)
                .rarity(Rarity.COMMON)
                .textVf("{D} : The next Permanent you play this turn costs {1} less. (Discard me from Reserve to do this.)")
                .flavorVf("You can't choose when and where a quick fix will be needed.")
                .nameVf("Mécanicienne de la Fonderie").build();

        Card hap = Card.builder()
                .cardType(CardType.SPELL)
                .faction(Faction.AXIOM)
                .id("ALT_CORE_B_AX_20_C")
                .editions(List.of(btg))
                .subTypes(List.of(subTypeManeuver))
                .costHand(1)
                .costReserve(2)
                .rarity(Rarity.COMMON)
                .textVf("Target Character switches Expeditions. (It leaves its Expedition and joins its controller's other Expedition.)")
                .nameVf("Happé").build();

        Card entr = Card.builder()
                .cardType(CardType.SPELL)
                .faction(Faction.AXIOM)
                .id("ALT_CORE_B_AX_22_C")
                .editions(List.of(btg))
                .subTypes(List.of(subTypeManeuver))
                .costHand(1)
                .costReserve(2)
                .rarity(Rarity.COMMON)
                .textVf("Activate the {j} triggers of target Permanent you control.")
                .nameVf("Entraînement Mécanique").build();

        cardRepository.save(sierra);
        cardRepository.save(inge);
        cardRepository.save(meca);
        cardRepository.save(hap);
        cardRepository.save(entr);

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

        DeckCard deckCard3 = DeckCard.builder()
                .nbCard(3)
                .pk(DeckCardId.builder().card(cards.get(2)).deck(deck).build())
                .build();

        DeckCard deckCard4 = DeckCard.builder()
                .nbCard(3)
                .pk(DeckCardId.builder().card(cards.get(3)).deck(deck).build())
                .build();

        DeckCard deckCard5 = DeckCard.builder()
                .nbCard(3)
                .pk(DeckCardId.builder().card(cards.get(4)).deck(deck).build())
                .build();

        deck.setCards(List.of(deckCard1, deckCard2, deckCard3, deckCard4, deckCard5));

        deckRepository.save(deck);
    }

}
