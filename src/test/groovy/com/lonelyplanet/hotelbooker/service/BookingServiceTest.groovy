package com.lonelyplanet.hotelbooker.service

import org.junit.Before
import org.junit.Test
import com.lonelyplanet.hotelbooker.model.*

/**
 *
 */
public class BookingServiceTest {

    TravellerService travellerService
    AccommodationService accommodationService
    BookingService bookingService;

    @Before
    void setup() {
        travellerService = new TravellerService()
        accommodationService = new AccommodationService()
        bookingService = new BookingService(travellerService, accommodationService)
    }

    @Test
    void shouldBookTravellerInToAccommodationIfHasCapacity() {
        //given
        Accommodation accommodation = new Accommodation([id: 0, totalCapacity: 1, freeCapacity: 1])

        Traveller traveller = new Traveller([id: 1])

        //when
        bookingService.book(accommodation, traveller)

        //then
        List<Traveller> bookings = bookingService.getBookings(accommodation)
        assert bookings.size() == 1
        assert bookings.contains(traveller)
        assert accommodation.freeCapacity == 0
    }

    @Test
    void shouldBookTravellersInToAccommodationIfHasCapacity() {
        //given
        Accommodation accommodation = new Accommodation([id: 0, totalCapacity: 2, freeCapacity: 2])

        Traveller travellerA = new Traveller([id: 1])
        Traveller travellerB = new Traveller([id: 2])

        //when
        bookingService.book(accommodation, travellerA)
        bookingService.book(accommodation, travellerB)

        //then
        List<Traveller> bookings = bookingService.getBookings(accommodation)
        assert bookings.size() == 2
        assert bookings.containsAll([travellerA, travellerB])
        assert accommodation.freeCapacity == 0
    }

    @Test(expected = BookingException)
    void shouldThrowErrorIfAccommodationHasNoCapacity() {
        //given
        Accommodation accommodation = new Accommodation([id: 0, totalCapacity: 1, freeCapacity: 0])

        Traveller traveller = new Traveller([id: 1])

        //when
        bookingService.book(accommodation, traveller)
    }
}
