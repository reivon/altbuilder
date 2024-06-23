package fr.reivon.altbuilder.domain.deck;

import fr.reivon.altbuilder.domain.user.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    Boolean secret;
    String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Customer author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.deck", cascade = CascadeType.ALL, orphanRemoval = true)
    List<DeckCard> cards = new ArrayList<>();

}
