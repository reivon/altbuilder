package fr.reivon.altbuilder.web.rest;

import fr.reivon.altbuilder.domain.deck.Deck;
import fr.reivon.altbuilder.domain.user.Customer;
import fr.reivon.altbuilder.service.DeckService;
import fr.reivon.altbuilder.web.rest.dto.deck.DeckDto;
import fr.reivon.altbuilder.web.rest.dto.deck.DeckResponseDto;
import fr.reivon.altbuilder.web.rest.mapper.deck.DeckMapper;
import fr.reivon.altbuilder.web.rest.mapper.deck.DeckResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller to manage a deck.
 */
@Slf4j
@RestController
@RequestMapping("/v1/deck")
public class DeckResourceV1 {

    DeckService deckService;
    DeckResponseMapper deckResponseMapper;
    DeckMapper deckMapper;

    public DeckResourceV1(DeckService deckService, DeckMapper deckMapper, DeckResponseMapper deckResponseMapper) {
        this.deckService = deckService;
        this.deckMapper = deckMapper;
        this.deckResponseMapper = deckResponseMapper;
    }

    /**
     * GET /deck/{id}
     * Get a deck by his id.
     * If it doesn't exist in database, we return a 404 status.
     *
     * @param deckId deck identifier to get
     * @return the deck if found, null otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeckResponseDto> getDeck(@PathVariable("id") Long deckId) {
        Deck deckToRetrieve = deckService.getById(deckId);
        if (deckToRetrieve == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deckResponseMapper.deckToDeckResponseDto(deckToRetrieve));
    }

    /**
     * POST /deck
     * Record a new valid deck in database
     * @param deckDto deck to create
     * @return the new deck saved if all is ok
     */
    @PostMapping()
    public DeckDto saveDeck(@RequestBody DeckDto deckDto) {
        Deck deckToSave = deckMapper.deckDtoToDeck(deckDto);

        // Get the user who save his deck
        Customer customer = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        deckToSave.setAuthor(customer);

        Deck deckSaved = deckService.save(deckToSave);

        return deckMapper.deckToDeckDto(deckSaved);
    }

    /**
     * DELETE /deck/{id}
     * Delete a deck by his id
     * @param deckId deck identifier to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeck(@PathVariable("id") Long deckId) {
        if (deckId == null) {
            log.debug("Someone try to delete a deck that doesn't exist");
            return;
        }
        deckService.delete(deckId);
    }

}
