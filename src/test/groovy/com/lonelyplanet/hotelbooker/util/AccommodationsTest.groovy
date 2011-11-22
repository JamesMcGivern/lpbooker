package com.lonelyplanet.hotelbooker.util;


import org.junit.Test

import com.lonelyplanet.hotelbooker.model.Accommodation

/**
 *
 */
public class AccommodationsTest {

    def expectedAccommodation = new Accommodation([
            id: 0,
            name: "Cremin Apartments",
            price: 253,
            attributes: ["internet", "bath", "24 reception", "phone", "gym"],
                    totalCapacity: 37,
                    freeCapacity: 36
    ])

    def expectedAccommodations = [
            new Accommodation([
                    id: 0,
                    name: "Cremin Apartments",
                    price: 253,
                    attributes: ["internet", "bath", "24 reception", "phone", "gym"],
                            totalCapacity: 37,
                            freeCapacity: 36
            ]),
            new Accommodation([
                    id: 1,
                    name: "Simonis Campsite",
                    price: 72,
                    attributes: ["restaurant","room service","gym","meeting rooms","quiet"],
                            totalCapacity: 96,
                            freeCapacity: 74
            ])
    ]

    @Test
    public void shouldCreateAccommodationFromJson() throws Exception {
        //given
        String json = """
{
    "id":0,
    "name":"Cremin Apartments",
    "price":253,
    "attributes":["internet","bath","24 reception","phone","gym"],
    "capacity":{
        "total":37,
        "free":36
    }
}
        """.trim()

        //when
        List<Accommodation> actual = Accommodations.parse(json)

        //then
        assert [expectedAccommodation] == actual
    }


    @Test
    public void shouldCreateAccommodationFromFile() throws Exception {
        //given
        File jsonFile = new File(this.getClass().getClassLoader().getResource("accommodation.json").toURI())

        //when
        List<Accommodation> actual = Accommodations.parse(jsonFile)

        //then
        assert [expectedAccommodation] == actual
    }


    @Test
    public void shouldCreateAccommodationsFromJson() throws Exception {
        //given
        String json = """
[
    {
        "id":0,
        "name":"Cremin Apartments",
        "price":253,
        "attributes":["internet","bath","24 reception","phone","gym"],
        "capacity":{
            "total":37,
            "free":36
        }
    },
    {
        "id":1,
        "name":"Simonis Campsite",
        "price":72,
        "attributes":["restaurant","room service","gym","meeting rooms","quiet"],
        "capacity":{
            "total":96,
            "free":74
        }
    }
]
        """.trim()

        //when
        List<Accommodation> actual = Accommodations.parse(json)

        //then
        assert expectedAccommodations == actual
    }


    @Test
    public void shouldCreateAccommodationsFromFile() throws Exception {
        //given
        File jsonFile = new File(this.getClass().getClassLoader().getResource("accommodations-two.json").toURI())

        //when
        List<Accommodation> actual = Accommodations.parse(jsonFile)

        //then
        assert expectedAccommodations == actual
    }

}
