package fr.reivon.altbuilder.web.consumers.altered.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConsumerResponse(@JsonProperty("hydra:member") List<ConsumerCard> cards) {
}
