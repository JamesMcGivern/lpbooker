package com.lonelyplanet.hotelbooker.service

import com.lonelyplanet.hotelbooker.model.Traveller

/**
 *
 */
class TravellerService {

    private Map<Long, Traveller> travellers

    TravellerService() {
        travellers = new HashMap<Long, Traveller>()
    }

    void addTraveller(Traveller traveller) {
        travellers.put(traveller.id, traveller)
    }

    Traveller getTravellerById(long id) {
        return travellers.get(id)
    }

    List<Traveller> getTravellers() {
        return new ArrayList<Traveller>(travellers.values())
    }
}
