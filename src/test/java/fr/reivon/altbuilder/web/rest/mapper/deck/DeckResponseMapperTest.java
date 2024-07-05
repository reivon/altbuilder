package fr.reivon.altbuilder.web.rest.mapper.deck;

import fr.reivon.altbuilder.domain.deck.Deck;
import fr.reivon.altbuilder.domain.deck.DeckCardId;
import fr.reivon.altbuilder.web.rest.dto.deck.DeckResponseDto;
import fr.reivon.altbuilder.web.rest.mapper.CardMapperImpl;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DeckResponseMapperImpl.class, CardMapperImpl.class})
public class DeckResponseMapperTest {

    @Autowired
    DeckResponseMapper deckResponseMapper;

    Deck deck;

    @BeforeEach
    void setup() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters
                // Explicitly state our desired string length and collections size.
                .stringLengthRange(3, 10)
                .collectionSizeRange(3, 10)
                .ignoreRandomizationErrors(false)
                // To override default initialization on object
                .overrideDefaultInitialization(true);
        //parameters.randomizationDepth(3);
        // Example to exclude from generation the field deck in class deckCardId
        parameters.excludeField(FieldPredicates.named("deck").and(FieldPredicates.inClass(DeckCardId.class)));
        // Example to exclude everything from “not.existing.pkg” Java package
        // parameters.excludeType(TypePredicates.inPackage("not.existing.pkg"));
        // Specify a custom randomizer
        // parameters.randomize(YearQuarter.class, new YearQuarterRandomizer());
        EasyRandom generator = new EasyRandom(parameters);
        deck = generator.nextObject(Deck.class);
    }

    @Test
    void deckToDeckResponseDto() {
        // GIVEN
        // setup()

        // WHEN
        DeckResponseDto result = deckResponseMapper.deckToDeckResponseDto(deck);

        // THEN
        Assertions.assertNotNull(result, "Result is not null");
        Assertions.assertEquals(result.getAuthor(), deck.getAuthor().getNickname(), "Nickname is well set");
        Assertions.assertEquals(result.getCards().size(), deck.getCards().size(), "Same number of cards");
        Assertions.assertEquals(result.getCards().get(0).getCard().getId(), deck.getCards().get(0).getPk().getCard().getId(), "Same first card");
        Assertions.assertEquals(result.getCards().get(0).getNbCard(), deck.getCards().get(0).getNbCard(), "Same number of first card");
    }
}