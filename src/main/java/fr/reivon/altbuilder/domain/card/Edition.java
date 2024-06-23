package fr.reivon.altbuilder.domain.card;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String nameVo;
    String nameVf;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "editions", fetch = FetchType.LAZY)
    private List<Card> cards;

}
