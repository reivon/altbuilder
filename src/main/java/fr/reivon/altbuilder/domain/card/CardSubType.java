package fr.reivon.altbuilder.domain.card;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CardSubType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String nameVo;
    String nameVf;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "subTypes", fetch = FetchType.LAZY)
    private List<Card> cards;

}
