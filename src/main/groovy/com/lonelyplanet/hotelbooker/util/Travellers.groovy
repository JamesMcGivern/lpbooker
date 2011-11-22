package com.lonelyplanet.hotelbooker.util

import com.lonelyplanet.hotelbooker.model.Criteria
import com.lonelyplanet.hotelbooker.model.Traveller
import com.lonelyplanet.hotelbooker.model.TravellerAndCriteria
import net.sf.json.JSON
import net.sf.json.groovy.JsonSlurper

/**
 *
 */
class Travellers {

    static List<TravellerAndCriteria> parse(String jsonString) {
        JSON json = new JsonSlurper().parseText(jsonString)
        parse(json)

    }

    static List<TravellerAndCriteria> parse(File jsonFile) {
        JSON json = new JsonSlurper().parse(jsonFile)
        parse(json)
    }

    static List<TravellerAndCriteria> parse(JSON json) {
        def travellers = []
        if (json.array)
            json.each {
                travellers << jsonToTravellerAndCriteria(it)
            }
        else
            travellers << jsonToTravellerAndCriteria(json)
        return travellers
    }

    private static TravellerAndCriteria jsonToTravellerAndCriteria(JSON json) {
        new TravellerAndCriteria(
                traveller: new Traveller([
                        id: json.id,
                        name: json.name
                ]),
                criteria: new Criteria().priceRange(json.priceRange.min..json.priceRange.max).attributes(json.requirements)
        )
    }
}
