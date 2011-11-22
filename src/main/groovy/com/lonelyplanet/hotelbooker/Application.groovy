package com.lonelyplanet.hotelbooker

import com.lonelyplanet.hotelbooker.service.AutomatedBooker
import com.lonelyplanet.hotelbooker.service.BookingService
import com.lonelyplanet.hotelbooker.service.AccommodationService
import com.lonelyplanet.hotelbooker.util.Accommodations
import com.lonelyplanet.hotelbooker.model.Accommodation
import com.lonelyplanet.hotelbooker.util.Travellers

import com.lonelyplanet.hotelbooker.model.Criteria
import com.lonelyplanet.hotelbooker.model.Traveller
import com.lonelyplanet.hotelbooker.service.TravellerService
import com.lonelyplanet.hotelbooker.model.AccommodationAndTravellers

/**
 *
 */
class Application {

    TravellerService travellerService
    AccommodationService accommodationService
    BookingService bookingService
    AutomatedBooker booker

    Application() {
        travellerService = new TravellerService()
        accommodationService = new AccommodationService()
        bookingService = new BookingService(travellerService, accommodationService)
        booker = new AutomatedBooker(travellerService: travellerService, accommodationService: accommodationService, bookingService: bookingService)
    }

    void loadAccommodations(File json) {
        accommodationService.addAll(Accommodations.parse(json))
    }

    void autobookTravellers(File json) {
        booker.autobook(Travellers.parse(json))
    }

    AccommodationAndTravellers getBookingsByAccommodationId(long id) {
        Accommodation accommodation = accommodationService.getAccommodationById(id)
        List<Traveller> travellers = bookingService.getBookings(accommodation)
        return new AccommodationAndTravellers(accommodation: accommodation, travellers: travellers)
    }

    AccommodationAndTravellers getBookingsByTravellerId(long id) {
        Traveller traveller = travellerService.getTravellerById(id)
        Accommodation accommodation = bookingService.getBookingsByTraveller(traveller)
        return new AccommodationAndTravellers(accommodation: accommodation, travellers: [traveller])
    }

    Accommodation findAccommodation(Criteria criteria) {
        return accommodationService.findAccommodation(criteria)
    }
}
