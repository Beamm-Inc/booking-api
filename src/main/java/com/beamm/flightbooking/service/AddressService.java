package com.beamm.flightbooking.service;

        import com.beamm.flightbooking.model.Address;
        import com.beamm.flightbooking.model.Airplane;

public interface AddressService {
    Address getAddressById(Integer id);
    // update
    Address saveAddress(Address address);

}
