package com.beamm.flightbooking.service;

import com.beamm.flightbooking.model.Booking;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingService {
    Page<Booking> getAllBookingsPaged(int pageNo);
    List<Booking> getAllBooking();
    Booking getBookingById(Integer id);
    void deleteBookingById(Integer id) throws Exception;
    void deleteBookingByTicketNumber(String ticketNumber) throws  Exception;
    Booking saveBooking(Booking booking);
    String randomAlphaNumericBookingRef(int count);
    Booking getByBookingByBookingReference(String confirmationCode);
}
