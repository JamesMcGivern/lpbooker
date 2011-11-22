package com.lonelyplanet.hotelbooker.service

import com.lonelyplanet.hotelbooker.model.Accommodation
import com.lonelyplanet.hotelbooker.model.TravellerAndCriteria
import com.lonelyplanet.hotelbooker.service.AccommodationService
import com.lonelyplanet.hotelbooker.service.BookingService

/**
 *
 */
class AutomatedBooker {

    TravellerService travellerService
    AccommodationService accommodationService
    BookingService bookingService

    void autobook(List<TravellerAndCriteria> travellers) {
        travellers.each {
            Accommodation accommodation = accommodationService.findAccommodation(it.criteria)
            if (accommodation) {
                bookingService.book(accommodation, it.traveller)
            }
        }
    }
}
