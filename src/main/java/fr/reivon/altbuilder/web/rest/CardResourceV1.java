package fr.reivon.altbuilder.web.rest;

import fr.reivon.altbuilder.service.CardService;
import fr.reivon.altbuilder.service.DeckService;
import fr.reivon.altbuilder.service.TestServiceToDelete;
import fr.reivon.altbuilder.web.consumers.altered.AlteredConsumers;
import fr.reivon.altbuilder.web.rest.dto.card.CardDto;
import fr.reivon.altbuilder.web.rest.dto.deck.DeckDto;
import fr.reivon.altbuilder.web.rest.mapper.CardMapper;
import fr.reivon.altbuilder.web.rest.mapper.DeckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLException;
import java.util.List;

@RestController
@RequestMapping("/v1/card")
public class CardResourceV1 {

    private static final Logger log = LoggerFactory.getLogger(CardResourceV1.class);

    private final CardService cardService;
    private final CardMapper cardMapper;

    private final TestServiceToDelete testServiceToDelete;
    private final AlteredConsumers alteredConsumers;

    public CardResourceV1(CardService cardService, CardMapper cardMapper, TestServiceToDelete testServiceToDelete, AlteredConsumers alteredConsumers) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
        this.testServiceToDelete = testServiceToDelete;
        this.alteredConsumers = alteredConsumers;
    }

    @GetMapping("/test")
    @ResponseBody
    public List<CardDto> saveTestDeck() throws SSLException {
        testServiceToDelete.initDB();
        //alteredConsumers.getAllCard();
        return cardMapper.cardToCardDto(cardService.getAll());
    }

}
