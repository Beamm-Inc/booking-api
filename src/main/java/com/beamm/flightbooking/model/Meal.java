package com.beamm.flightbooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.Serializable;

public enum Meal //implements Serializable
//
{
    @JsonProperty("normal")
    NORMAL,
    @JsonProperty("vegie")
    VEGIE,
    @JsonProperty("halal")
    HALAL,
    @JsonProperty("diabetic")
    DIABETIC
}
