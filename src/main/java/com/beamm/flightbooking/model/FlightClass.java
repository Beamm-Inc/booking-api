package com.beamm.flightbooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public enum FlightClass
{
    @JsonProperty("first")
    FIRST,
    @JsonProperty("business")
    BUSINESS,
    @JsonProperty("economy")
    ECONOMY;
}
