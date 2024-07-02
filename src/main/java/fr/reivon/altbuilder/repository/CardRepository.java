package fr.reivon.altbuilder.repository;

import fr.reivon.altbuilder.domain.card.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    @Query("select c from Card c")
    List<Card> getAll();

}
