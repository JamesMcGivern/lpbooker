package com.lonelyplanet.hotelbooker.service

import com.lonelyplanet.hotelbooker.model.Accommodation

/**
 *
 */
class SearchResult {

    Accommodation accommodation
    int score


    boolean equals(o) {
        if (this.is(o)) return true;
        if (getClass() != o.class) return false;

        SearchResult that = (SearchResult) o;

        if (score != that.score) return false;
        if (accommodation != that.accommodation) return false;

        return true;
    }

    int hashCode() {
        int result;
        result = (accommodation != null ? accommodation.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }
}
