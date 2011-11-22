package com.lonelyplanet.hotelbooker.service

import com.lonelyplanet.hotelbooker.model.Accommodation
import com.lonelyplanet.hotelbooker.model.Criteria

/**
 *
 */
class AccommodationSearchScorer {

    int score(Accommodation accommodation, Criteria criteria) {
        Integer score = 0
        score += scorePrice(criteria, accommodation)
        score += scoreAttributes(criteria, accommodation)
        return score
    }

    private int scorePrice(Criteria criteria, Accommodation accommodation) {
        criteria.priceRange && criteria.priceRange.containsWithinBounds(accommodation.price) ?  1 :0
    }

    private int scoreAttributes(Criteria criteria, Accommodation accommodation) {
        criteria.attributes ? accommodation.attributes.intersect(criteria.attributes).size() : 0
    }
}
