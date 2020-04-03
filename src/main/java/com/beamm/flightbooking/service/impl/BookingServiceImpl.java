package com.beamm.flightbooking.service.impl;

import com.beamm.flightbooking.model.Booking;
import com.beamm.flightbooking.model.Passenger;
import com.beamm.flightbooking.model.Trip;
import com.beamm.flightbooking.repository.BookingRepository;
import com.beamm.flightbooking.repository.TripRepository;
import com.beamm.flightbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    TripRepository tripRepository;

    @Override
    public Page<Booking> getAllBookingsPaged(int pageNo) {
        return bookingRepository.findAll(PageRequest.of(pageNo, 10));
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    //@Transactional
    public void deleteBookingById(Integer id) throws Exception {
        Optional<Booking> booking = bookingRepository.findById(id);

        if (booking.isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            throw new Exception("No employee record exist for given id");
        }

    }

//    @Override
//    @Transactional
//    public void deleteBookingByTicketNumber(String ticketNumber) throws Exception {
//
//        Optional<Trip> trip = Optional.ofNullable(tripRepository.findByTicketNumber(ticketNumber));
//        if (trip.isPresent()) {
//            tripRepository.findByTicketNumber(ticketNumber);
//            HttpStatus.valueOf("Ticket deleted successfully.");
//        } else {
//            throw new Exception("No record exist for given ticket Number");
//        }
//    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public String randomAlphaNumericBookingRef(int count) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    @Override
    public Booking getByBookingByBookingReference(String confirmationCode) {
        return bookingRepository.findByBookingReference(confirmationCode);
    }
//
//    @Override
////    public Passenger addPasseneger(Passenger passenger) {
////        //return bookingRepository.addPassenger(passenger);
////        return  bookingRepository.addPassenger(passenger);
////
////    }
}
