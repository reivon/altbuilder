package fr.reivon.altbuilder.repository;

import fr.reivon.altbuilder.domain.deck.Deck;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeckRepository extends CrudRepository<Deck, Long> {

    @Query("select d from Deck d")
    List<Deck> getAll();

}
