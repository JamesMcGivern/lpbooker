package com.lonelyplanet.hotelbooker.service

import org.junit.Test
import org.junit.Before
import com.lonelyplanet.hotelbooker.service.AccommodationService
import com.lonelyplanet.hotelbooker.service.BookingService
import com.lonelyplanet.hotelbooker.util.Accommodations
import com.lonelyplanet.hotelbooker.util.Travellers
import com.lonelyplanet.hotelbooker.model.TravellerAndCriteria

import com.lonelyplanet.hotelbooker.service.AutomatedBooker

/**
 *
 */
public class AutomatedBookerTest {

    TravellerService travellerService
    AccommodationService accommodationService
    BookingService bookingService
    AutomatedBooker booker

    @Before
    void setup() {
        travellerService = new TravellerService()
        accommodationService = new AccommodationService()
        bookingService = new BookingService(travellerService, accommodationService)
        booker = new AutomatedBooker(accommodationService: accommodationService, bookingService: bookingService)
    }

    @Test
    void shouldBookSuitableAccommodationForTraveller() {
        //given
        accommodationService.addAll(Accommodations.parse(new File(AutomatedBookerTest.class.classLoader.getResource("accommodations-two.json").toURI())))

        //when
        List<TravellerAndCriteria> travellers = Travellers.parse(new File(this.getClass().getClassLoader().getResource("Travellers-two.json").toURI()))
        booker.autobook(travellers)

        //then
        assert bookingService.getBookings().size() == 2
    }
}
