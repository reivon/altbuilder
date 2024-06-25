package fr.reivon.altbuilder.web.rest;

import fr.reivon.altbuilder.domain.deck.Deck;
import fr.reivon.altbuilder.service.DeckService;
import fr.reivon.altbuilder.web.rest.dto.DeckDto;
import fr.reivon.altbuilder.web.rest.mapper.DeckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller to manage a deck.
 */
@RestController
@RequestMapping("/v1/deck")
public class DeckResourceV1 {

    private static final Logger LOG = LoggerFactory.getLogger(DeckResourceV1.class);

    DeckService deckService;
    DeckMapper deckMapper;

    public DeckResourceV1(DeckService deckService, DeckMapper deckMapper) {
        this.deckService = deckService;
        this.deckMapper = deckMapper;
    }

    /**
     * GET /deck/{id}
     * Get a deck by his id.
     * If doesn't exist in database, we return a 404 status.
     *
     * @param deckId
     * @return the deck if found, null otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeckDto> getDeck(@PathVariable("id") Long deckId) {
        Deck deckToRetrieve = deckService.getById(deckId);
        if (deckToRetrieve == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deckMapper.deckToDeckDto(deckToRetrieve));
    }

    @PostMapping()
    public DeckDto saveDeck(@RequestBody DeckDto deckDto) {
        Deck deckToSave = deckMapper.deckDtoToDeck(deckDto);
        Deck deckSaved = deckService.save(deckToSave);
        return deckMapper.deckToDeckDto(deckSaved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeck(DeckDto deckDto) {
        if (deckDto == null || deckDto.getId() == null) {
            LOG.debug("Someone try to delete a deck that doesn't exist");
            return;
        }
        deckService.delete(deckDto.getId());
    }

}
