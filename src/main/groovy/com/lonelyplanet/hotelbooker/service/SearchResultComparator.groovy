package com.lonelyplanet.hotelbooker.service

/**
 *
 */
class SearchResultComparator implements Comparator<SearchResult> {

    int compare(SearchResult left, SearchResult right) {
        if(left.score == right.score)
            return left.accommodation.id.compareTo(right.accommodation.id) //ascending order
        return right.score.compareTo(left.score) //descending order
    }

}
