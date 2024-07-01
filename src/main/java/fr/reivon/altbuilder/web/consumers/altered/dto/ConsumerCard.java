package fr.reivon.altbuilder.web.consumers.altered.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConsumerCard(String reference, ConsumerCardType cardType, List<ConsumerCardSubType> cardSubTypes,
                           ConsumerRarity rarity, ConsumerAssets assets, ConsumerMainFaction mainFaction,
                           String name, ConsumerElement elements, String collectorNumberFormatted) {
}