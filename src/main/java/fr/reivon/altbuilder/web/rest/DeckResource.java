package fr.reivon.altbuilder.web.rest;

import fr.reivon.altbuilder.domain.deck.Deck;
import fr.reivon.altbuilder.service.DeckService;
import fr.reivon.altbuilder.web.rest.dto.DeckDto;
import fr.reivon.altbuilder.web.rest.mapper.DeckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/deck")
public class DeckResource {

    private static final Logger log = LoggerFactory.getLogger(DeckResource.class);

    DeckService deckService;
    DeckMapper deckMapper;

    public DeckResource(DeckService deckService, DeckMapper deckMapper) {
        this.deckService = deckService;
        this.deckMapper = deckMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeckDto getDeck(@PathVariable("id") Long deckId) {
        return deckMapper.deckToDeckDto(deckService.getById(deckId));
    }

    @PostMapping()
    public DeckDto saveDeck(DeckDto deckDto) {
        Deck deckToSave = deckMapper.deckDtoToDeck(deckDto);
        Deck deckSaved = deckService.save(deckToSave);
        return deckMapper.deckToDeckDto(deckSaved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeck(DeckDto deckDto) {
        if (deckDto == null || deckDto.getId() == null) {
            log.debug("Someone try to delete a deck that doesn't exist");
            return;
        }
        deckService.delete(deckDto.getId());
    }

}
