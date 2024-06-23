package fr.reivon.altbuilder.service;

import fr.reivon.altbuilder.domain.card.Card;
import fr.reivon.altbuilder.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAll() {
        return cardRepository.getAll();
    }

    public Long countCard() {
        return cardRepository.count();
    }

    public Card getCard(String alteredId) {
        return cardRepository.findByAlteredId(alteredId);
    }

}
