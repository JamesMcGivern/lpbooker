package com.lonelyplanet.hotelbooker

import org.junit.Test

import org.junit.Before

import com.lonelyplanet.hotelbooker.model.AccommodationAndTravellers
import com.lonelyplanet.hotelbooker.model.Accommodation
import com.lonelyplanet.hotelbooker.model.Criteria
import com.lonelyplanet.hotelbooker.model.Traveller

/**
 *
 */
public class ApplicationTest {

    Application application

    @Before
    void setup() {
        application = new Application()
    }

    @Test
    void shouldLoadAccommodationsFromDisk() {
        //given
        File accommodationFile = new File(ApplicationTest.class.classLoader.getResource("accommodations-two.json").toURI())

        //when
        application.loadAccommodations(accommodationFile)

        //then
        assert application.accommodationService.getAccommodations().size() == 2
    }

    @Test
    void shouldLoadTravellersFromDiskAndAutoBookThem() {
        //given
        application.loadAccommodations(new File(ApplicationTest.class.classLoader.getResource("accommodations-all.json").toURI()))

        //when
        application.autobookTravellers(new File(ApplicationTest.class.classLoader.getResource("travellers-two.json").toURI()))

        //then
        assert application.bookingService.bookings.size() == 2
        assert application.accommodationService.accommodations.size() == 1000
    }

    @Test
    void shouldListTravellerBookingForAccommodationId() {
        //given
        application.loadAccommodations(new File(ApplicationTest.class.classLoader.getResource("accommodations-all.json").toURI()))
        application.autobookTravellers(new File(ApplicationTest.class.classLoader.getResource("travellers-two.json").toURI()))

        //when
        AccommodationAndTravellers bookings = application.getBookingsByAccommodationId(10)

        //then

        bookings.accommodation == new Accommodation(id: 10, name: "Thiel Retreat", price: 252, attributes: ["close to transport", "internet", "24 reception", "bar", "parking", "air conditioning", "gym", "meeting rooms", "quiet"], totalCapacity: 63, freeCapacity: 34)
        assert bookings.travellers.size() == 1
        assert bookings.travellers[0] == new Traveller(id: 1, name: "Mrs. Everardo Pacocha")
    }

    @Test
    void shouldListAccommodationBookingForTravellerId() {
        //given
        application.loadAccommodations(new File(ApplicationTest.class.classLoader.getResource("accommodations-all.json").toURI()))
        application.autobookTravellers(new File(ApplicationTest.class.classLoader.getResource("travellers-two.json").toURI()))

        //when
        AccommodationAndTravellers actual = application.getBookingsByTravellerId(0)

        //then
        Accommodation expectedAccommodation = new Accommodation(
                id: 105,
                name: "Stanton B&B",
                price: 217,
                attributes: ["close to transport", "internet", "bath", "restaurant", "phone", "parking", "air conditioning", "gym"],
                totalCapacity: 154,
                freeCapacity: 123
        )
        Traveller expectedTraveller = new Traveller(
                    id: 0,
                    name: "Mrs. Tess Goyette"
            )

        AccommodationAndTravellers expected = new AccommodationAndTravellers(accommodation: expectedAccommodation, travellers: [expectedTraveller])
        assert actual.equals(expected)
    }

    @Test
    void shouldFindAccommodationForCriteria() {
        //given
        application.loadAccommodations(new File(ApplicationTest.class.classLoader.getResource("accommodations-all.json").toURI()))
        application.autobookTravellers(new File(ApplicationTest.class.classLoader.getResource("travellers-two.json").toURI()))

        Criteria criteria = new Criteria().priceRange(216..218).attributes("close to transport", "internet", "bath", "restaurant", "phone", "parking", "air conditioning", "gym")

        //when
        Accommodation actual = application.findAccommodation(criteria)

        //then
        Accommodation expected = new Accommodation(
                id: 105,
                name: "Stanton B&B",
                price: 217,
                attributes: ["close to transport", "internet", "bath", "restaurant", "phone", "parking", "air conditioning", "gym"],
                totalCapacity: 154,
                freeCapacity: 123
        )
        assert actual.equals(expected)
    }
}
