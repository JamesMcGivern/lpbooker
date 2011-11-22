package com.lonelyplanet.hotelbooker.service

import com.lonelyplanet.hotelbooker.model.Accommodation
import com.lonelyplanet.hotelbooker.model.Traveller
import com.lonelyplanet.hotelbooker.model.TravellerAndCriteria

/**
 *
 */
class BookingService {

    private TravellerService travellerService
    private AccommodationService accommodationService

    private Map<Long, List<Traveller>> bookings = new HashMap<Long, List<Traveller>>()

    BookingService(TravellerService travellerService, AccommodationService accommodationService) {
        this.travellerService = travellerService
        this.accommodationService = accommodationService
    }

    void book(Accommodation accommodation, Traveller traveller) {
        if(accommodation.insufficient) {
            throw new BookingException("Could not book $traveller in to $accommodation : No Free Space")
        }
        else {
            List<Traveller> accommodationBookings = bookings.get(accommodation.id)
            if(accommodationBookings == null) {
                bookings.put(accommodation.id, [traveller])
            }
            else {
                accommodationBookings.add(traveller)
            }
            accommodation.freeCapacity--
            travellerService.addTraveller(traveller)
        }
    }

    Map<Long, List<Traveller>> getBookings() {
        return bookings
    }

    List<Traveller> getBookings(Accommodation accommodation) {
        return bookings.get(accommodation.id)
    }

    Accommodation getBookingsByTraveller(Traveller traveller) {
        long accommodationId = bookings.find {long key, List<Traveller> value -> value.find {Traveller t -> t.id == traveller.id} != null}.key
        accommodationService.getAccommodationById(accommodationId)
    }
}
