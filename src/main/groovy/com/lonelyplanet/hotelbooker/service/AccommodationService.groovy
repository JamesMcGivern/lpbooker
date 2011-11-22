package com.lonelyplanet.hotelbooker.service

import com.lonelyplanet.hotelbooker.model.Accommodation
import com.lonelyplanet.hotelbooker.model.TravellerAndCriteria
import java.rmi.NoSuchObjectException
import com.lonelyplanet.hotelbooker.model.Criteria

/**
 *
 */
class AccommodationService {

    private static final Comparator<SearchResult> ACCOMMODATION_COMPARATOR = new SearchResultComparator()

    private AccommodationSearchScorer scorer = new AccommodationSearchScorer()
    private List<Accommodation> accommodations = []

    List<Accommodation> getAccommodations() {
        return accommodations
    }

    void add(Accommodation accommodation) {
        accommodations << accommodation
    }

    void addAll(Collection<Accommodation> accommodations) {
        this.accommodations.addAll(accommodations)
    }

    int total() {
        return accommodations.size()
    }

    Accommodation getAccommodationById(long id) {
        Accommodation accommodation = accommodations.find { it.id == id }
        if (accommodation)
            return accommodation
        else
            throw new NoSuchObjectException("No accommodation with id=$id exists")
    }

    Accommodation findAccommodation(Criteria criteria) {
        List<SearchResult> results = accommodations.collect {new SearchResult(accommodation: it, score: scorer.score(it, criteria))}
        if(results.isEmpty())
            return null

       return results.findAll {it.accommodation.freeCapacity > 0}.sort(ACCOMMODATION_COMPARATOR).first().accommodation
    }
}
