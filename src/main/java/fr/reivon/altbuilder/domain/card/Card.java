package fr.reivon.altbuilder.domain.card;

import fr.reivon.altbuilder.domain.CardType;
import fr.reivon.altbuilder.domain.Faction;
import fr.reivon.altbuilder.domain.Rarity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Card {

    @Id
    String id;

    Faction faction;
    Rarity rarity;
    CardType cardType;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<CardSubType> subTypes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Edition> editions;

    Integer costHand;
    Integer costReserve;

    String nameVo;
    String nameVf;
    String textVo;
    String textVf;
    String flavorVo;
    String flavorVf;

    // Only for character
    Integer forestPower;
    Integer mountainPower;
    Integer waterPower;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return Objects.equals(id, card.id)
                && faction == card.faction
                && rarity == card.rarity
                && cardType == card.cardType
                && Objects.equals(costHand, card.costHand)
                && Objects.equals(costReserve, card.costReserve)
                && Objects.equals(nameVo, card.nameVo)
                && Objects.equals(nameVf, card.nameVf)
                && Objects.equals(textVo, card.textVo)
                && Objects.equals(textVf, card.textVf)
                && Objects.equals(flavorVo, card.flavorVo)
                && Objects.equals(flavorVf, card.flavorVf)
                && Objects.equals(forestPower, card.forestPower)
                && Objects.equals(mountainPower, card.mountainPower)
                && Objects.equals(waterPower, card.waterPower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faction, rarity, cardType, costHand, costReserve, nameVo, nameVf, textVo, textVf, flavorVo, flavorVf, forestPower, mountainPower, waterPower);
    }

    @Override
    public String toString() {
        return "Card{" +
                ", id='" + id + '\'' +
                ", faction=" + faction +
                ", rarity=" + rarity +
                ", cardType=" + cardType +
                ", costHand=" + costHand +
                ", costReserve=" + costReserve +
                ", nameVo='" + nameVo + '\'' +
                ", nameVf='" + nameVf + '\'' +
                ", textVo='" + textVo + '\'' +
                ", textVf='" + textVf + '\'' +
                ", flavorVo='" + flavorVo + '\'' +
                ", flavorVf='" + flavorVf + '\'' +
                ", forestPower=" + forestPower +
                ", mountainPower=" + mountainPower +
                ", waterPower=" + waterPower +
                '}';
    }
}
