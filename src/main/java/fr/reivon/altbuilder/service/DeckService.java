package fr.reivon.altbuilder.service;

import fr.reivon.altbuilder.domain.deck.Deck;
import fr.reivon.altbuilder.repository.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public List<Deck> getAll() {
        return deckRepository.getAll();
    }

    public Deck getById(long deckId) {
        return deckRepository.findById(deckId).orElse(null);
    }

    public Deck save(Deck deck) {
        return deckRepository.save(deck);
    }

    public void delete(Long deckid) {
        deckRepository.deleteById(deckid);
    }
}
