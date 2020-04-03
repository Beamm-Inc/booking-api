package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.LuggageAllowance;
import com.beamm.flightbooking.repository.LuggageAllowanceRepository;
import com.beamm.flightbooking.service.LuggageAllowanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LuggageAllowanceServiceImpl implements LuggageAllowanceService {
    @Autowired
    LuggageAllowanceRepository luggageAllowanceRepository;

    @Override
    public LuggageAllowance getLuggageAllowanceById(Integer id) {
        return luggageAllowanceRepository.findById(id).orElse(null);
    }

    // TODO: implement
    @Override
    public LuggageAllowance getLuggageAllowanceById(String ticketNumber) {
        return null;
    }

    @Override
    public LuggageAllowance saveLuggageAllowance(LuggageAllowance luggageAllowance) {
        return luggageAllowanceRepository.save(luggageAllowance);
    }
}
