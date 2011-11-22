package com.lonelyplanet.hotelbooker.util;


import com.lonelyplanet.hotelbooker.model.Criteria
import com.lonelyplanet.hotelbooker.model.Traveller
import com.lonelyplanet.hotelbooker.model.TravellerAndCriteria
import org.junit.Test

/**
 *
 */
public class TravellersTest {

    def expectedTravellerAndCriteria = new TravellerAndCriteria(
            traveller: new Traveller([
                    id: 0,
                    name: "Mrs. Tess Goyette"
            ]),
            criteria: new Criteria().priceRange(86..87).attributes("close to transport", "internet", "bath", "gym")
    )

    def expectedTravellersAndCriteria = [
            new TravellerAndCriteria(
                    traveller: new Traveller([
                            id: 0,
                            name: "Mrs. Tess Goyette"
                    ]),
                    criteria: new Criteria().priceRange(86..87).attributes("close to transport", "internet", "bath", "gym")
            ),
            new TravellerAndCriteria(
                    traveller: new Traveller([
                            id: 1,
                            name: "Mrs. Everardo Pacocha",
                    ]),
                    criteria: new Criteria().priceRange(40..291).attributes("close to transport", "bar", "meeting rooms")
            )
    ]

    @Test
    public void shouldCreateTravellerFromJson() throws Exception {
        //given
        String json = """
{
    "id":0,
    "name":"Mrs. Tess Goyette",
    "priceRange":{
        "min":86,
        "max":87
    },
    "requirements":["close to transport","internet","bath","gym"]
}
        """.trim()

        //when
        List<TravellerAndCriteria> actual = Travellers.parse(json)

        //then
        assert [expectedTravellerAndCriteria] == actual
    }


    @Test
    public void shouldCreateTravellerFromFile() throws Exception {
        //given
        File jsonFile = new File(this.getClass().getClassLoader().getResource("Traveller.json").toURI())

        //when
        List<TravellerAndCriteria> actual = Travellers.parse(jsonFile)

        //then
        assert [expectedTravellerAndCriteria] == actual
    }


    @Test
    public void shouldCreateTravellersFromJson() throws Exception {
        //given
        String json = """
[
    {
        "id":0,
        "name":"Mrs. Tess Goyette",
        "priceRange":{
            "min":86,
            "max":87
        },
        "requirements":["close to transport","internet","bath","gym"]
    },
    {
        "id":1,
        "name":"Mrs. Everardo Pacocha",
        "priceRange":{
            "min":40,
            "max":291
        },
        "requirements":["close to transport","bar","meeting rooms"]
    }
]
        """.trim()

        //when
        List<TravellerAndCriteria> actual = Travellers.parse(json)

        //then
        assert expectedTravellersAndCriteria == actual
    }


    @Test
    public void shouldCreateTravellersFromFile() throws Exception {
        //given
        File jsonFile = new File(this.getClass().getClassLoader().getResource("Travellers-two.json").toURI())

        //when
        List<TravellerAndCriteria> actual = Travellers.parse(jsonFile)

        //then
        assert expectedTravellersAndCriteria == actual
    }

}
