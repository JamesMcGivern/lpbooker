package com.lonelyplanet.hotelbooker.service

import com.lonelyplanet.hotelbooker.model.Accommodation
import com.lonelyplanet.hotelbooker.model.Criteria
import com.lonelyplanet.hotelbooker.model.Traveller
import com.lonelyplanet.hotelbooker.model.TravellerAndCriteria
import java.rmi.NoSuchObjectException
import org.junit.Before
import org.junit.Test

/**
 *
 */
public class AccommodationServiceTest {

    AccommodationService service

    @Before
    void setup() {
        service = new AccommodationService()
    }

    @Test
    public void shouldAddNewAccommodation() throws Exception {
        //given
        Accommodation accommodation = new Accommodation([
                id: 0,
                name: "Cremin Apartments",
                price: 253,
                attributes: ["internet", "bath", "24 reception", "phone", "gym"],
                totalCapacity: 37,
                freeCapacity: 36
        ])

        //when
        service.add(accommodation)

        //then
        assert service.total() == 1
        assert accommodation == service.accommodations[0]
    }

    @Test
    public void shouldGetAccommodationById() throws Exception {
        //given
        Accommodation accommodationA = new Accommodation([
                id: 0,
                name: "Cremin Apartments",
                price: 253,
                attributes: ["internet", "bath", "24 reception", "phone", "gym"],
                totalCapacity: 37,
                freeCapacity: 36

        ])
        Accommodation accommodationB = new Accommodation([
                id: 1,
                name: "Hilton",
                price: 6000,
                attributes: ["internet", "bath", "24 reception", "phone", "gym", "helipad"],
                totalCapacity: 650,
                freeCapacity: 10
        ])
        service.add(accommodationA)
        service.add(accommodationB)

        //when
        Accommodation accommodation = service.getAccommodationById(0)

        //then
        assert accommodation == accommodationA
    }

    @Test(expected = NoSuchObjectException)
    public void shouldThrowErrorIfGetAccommodationByIdDoesNotExist() throws Exception {
        //when
        service.getAccommodationById(0)
    }

    @Test
    void shouldFindAccommodationForTravellerWhenNoAccommodationsExist() {
        //given
        TravellerAndCriteria travellerAndCriteria = new TravellerAndCriteria(
                traveller: new Traveller([
                        id: 0,
                        name: "Mrs. Tess Goyette"
                ]),
                criteria: new Criteria().priceRange(86..87).attributes("close to transport", "internet", "bath", "gym")
        )

        //when
        Accommodation actual = service.findAccommodation(travellerAndCriteria.criteria)

        //then
        assert actual == null
    }

    @Test
    void shouldFindBestAccommodationForTraveller() {
        //given
        Accommodation accommodationA = new Accommodation([
                id: 0,
                name: "Cremin Apartments",
                price: 253,
                attributes: ["internet", "bath", "24 reception", "phone", "gym"],
                totalCapacity: 37,
                freeCapacity: 36
        ])
        Accommodation accommodationB = new Accommodation([
                id: 1,
                name: "Hilton",
                price: 6000,
                attributes: ["internet", "bath", "24 reception", "phone", "gym", "helipad"],
                totalCapacity: 650,
                freeCapacity: 10
        ])
        service.add(accommodationA)
        service.add(accommodationB)

        TravellerAndCriteria travellerAndCriteria = new TravellerAndCriteria(
                traveller: new Traveller([
                        id: 0,
                        name: "Mrs. Tess Goyette"
                ]),
                criteria: new Criteria().priceRange(1000..10000).attributes("internet", "bath", "phone", "helipad")
        )

        //when
        Accommodation actual = service.findAccommodation(travellerAndCriteria.criteria)

        //then
        assert actual == accommodationB
    }
}
