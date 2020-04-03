package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.LuggageAllowance;


public interface LuggageAllowanceService {
    LuggageAllowance getLuggageAllowanceById(Integer id);
    LuggageAllowance getLuggageAllowanceById(String ticketNumber);
    LuggageAllowance saveLuggageAllowance(LuggageAllowance luggageAllowance);
}
