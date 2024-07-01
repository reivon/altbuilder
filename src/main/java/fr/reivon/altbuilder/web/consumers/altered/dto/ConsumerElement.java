package fr.reivon.altbuilder.web.consumers.altered.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsumerElement(@JsonProperty("PERMANENT") String permanentNumber,
                              @JsonProperty("RESERVE") String reserveNumber,
                              @JsonProperty("MAIN_COST") String handCost,
                              @JsonProperty("RECALL_COST") String reserveCost,
                              @JsonProperty("MAIN_EFFECT") String mainEffect,
                              @JsonProperty("OCEAN_POWER") String oceanPower,
                              @JsonProperty("MOUNTAIN_POWER") String mountainPower,
                              @JsonProperty("FOREST_POWER") String forestPower,
                              @JsonProperty("ECHO_EFFECT") String echoEffect
) {
}
