package fr.reivon.altbuilder.web.rest;

import fr.reivon.altbuilder.service.CardService;
import fr.reivon.altbuilder.service.DeckService;
import fr.reivon.altbuilder.service.TestServiceToDelete;
import fr.reivon.altbuilder.web.rest.dto.CardDto;
import fr.reivon.altbuilder.web.rest.dto.DeckDto;
import fr.reivon.altbuilder.web.rest.mapper.CardMapper;
import fr.reivon.altbuilder.web.rest.mapper.DeckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardResource {

    private static final Logger log = LoggerFactory.getLogger(CardResource.class);

    private final CardService cardService;
    private final CardMapper cardMapper;

    private final TestServiceToDelete testServiceToDelete;
    private final DeckMapper deckMapper;
    private final DeckService deckService;

    public CardResource(CardService cardService, CardMapper cardMapper, TestServiceToDelete testServiceToDelete, DeckMapper deckMapper, DeckService deckService) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
        this.testServiceToDelete = testServiceToDelete;
        this.deckMapper = deckMapper;
        this.deckService = deckService;
    }

    @GetMapping("test")
    public List<CardDto> saveTestDeck() {
        testServiceToDelete.initDB();
        return cardMapper.cardToCardDto(cardService.getAll());
    }

    @GetMapping("testDeck")
    public List<DeckDto> getTestDeck() {
        return deckMapper.deckToDeckDto(deckService.getAll());
    }

}
