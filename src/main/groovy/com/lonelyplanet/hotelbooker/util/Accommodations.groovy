package com.lonelyplanet.hotelbooker.util

import net.sf.json.groovy.JsonSlurper
import net.sf.json.JSON

import com.lonelyplanet.hotelbooker.model.Accommodation

/**
 *
 */
class Accommodations {

    static List<Accommodation> parse(String jsonString) {
        JSON json = new JsonSlurper().parseText(jsonString)
        parse(json)

    }

    static List<Accommodation> parse(File jsonFile) {
        JSON json = new JsonSlurper().parse(jsonFile)
        parse(json)
    }

    static List<Accommodation> parse(JSON json) {
        def accommodations = []
        if(json.array)
                json.each {
                    accommodations << jsonToAccommodation(it)
                }
        else
            accommodations << jsonToAccommodation(json)
        return accommodations
    }

    private static Accommodation jsonToAccommodation(JSON json) {
        new Accommodation(
                id: json.id,
                name: json.name,
                price: json.price,
                attributes: json.attributes,
                totalCapacity: json.capacity.total,
                freeCapacity: json.capacity.free
        )

    }
}
