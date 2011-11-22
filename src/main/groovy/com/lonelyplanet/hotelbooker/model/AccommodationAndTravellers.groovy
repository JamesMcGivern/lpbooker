package com.lonelyplanet.hotelbooker.model

/**
 *
 */
class AccommodationAndTravellers {

    Accommodation accommodation
    List<Traveller> travellers = []


    boolean equals(o) {
        if (getClass() != o.class) return false;

        AccommodationAndTravellers that = (AccommodationAndTravellers) o;

        if (accommodation != that.accommodation) return false;
        if (travellers != that.travellers) return false;

        return true;
    }

    int hashCode() {
        int result;
        result = (accommodation != null ? accommodation.hashCode() : 0);
        result = 31 * result + (travellers != null ? travellers.hashCode() : 0);
        return result;
    }
}
