package com.lonelyplanet.hotelbooker.model

/**
 *
 */
class TravellerAndCriteria {
    Traveller traveller
    Criteria criteria


    boolean equals(o) {
        if (this.is(o)) return true;
        if (getClass() != o.class) return false;

        TravellerAndCriteria that = (TravellerAndCriteria) o;

        if (criteria != that.criteria) return false;
        if (traveller != that.traveller) return false;

        return true;
    }

    int hashCode() {
        int result;
        result = (traveller != null ? traveller.hashCode() : 0);
        result = 31 * result + (criteria != null ? criteria.hashCode() : 0);
        return result;
    }


    String toString() {
        "{ traveller: $traveller, criteria: $criteria }"
    }

}
