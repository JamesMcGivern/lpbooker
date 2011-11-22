package com.lonelyplanet.hotelbooker.service

import org.junit.Before
import com.lonelyplanet.hotelbooker.model.Accommodation
import com.lonelyplanet.hotelbooker.model.Criteria
import org.junit.Test;

/**
 *
 */
public class AccommodationSearchScorerTest {

    AccommodationSearchScorer scorer
    Accommodation accommodation

    @Before
    void setup() {
        scorer = new AccommodationSearchScorer()

        accommodation = new Accommodation([
                id: 1,
                name: "Hotel",
                price: 30,
                attributes: ["internet", "pool", "moat"]
        ])
    }

    @Test
    void shouldScoreZeroIfNoCriteriaSet() {
        //given
        Criteria criteria = new Criteria()

        //when
        int score = scorer.score(accommodation, criteria)

        //then
        assert score == 0
    }

    @Test
    void shouldScoreZeroIfPriceRangeCriteriaSetAndNotMet() {
        //given
        Criteria criteria = new Criteria().priceRange(20 .. 29)

        //when
        int score = scorer.score(accommodation, criteria)

        //then
        assert score == 0
    }

    @Test
    void shouldScoreOneIfPriceRangeCriteriaSetAndMet() {
        //given
        Criteria criteria = new Criteria().priceRange(25 .. 35)

        //when
        int score = scorer.score(accommodation, criteria)

        //then
        assert score == 1
    }

    @Test
    void shouldScoreZeroIfNoAttributesCriteriaSetAndMet() {
        //given
        Criteria criteria = new Criteria().attribute("library")

        //when
        int score = scorer.score(accommodation, criteria)

        //then
        assert score == 0
    }

    @Test
    void shouldScoreOneIfOneAttributeCriteriaSetAndMet() {
        //given
        Criteria criteria = new Criteria().attribute("internet")

        //when
        int score = scorer.score(accommodation, criteria)

        //then
        assert score == 1
    }

    @Test
    void shouldScoreTwoIfTwoAttributeCriteriaSetAndMet() {
        //given
        Criteria criteria = new Criteria().attributes(["internet", "pool"])

        //when
        int score = scorer.score(accommodation, criteria)

        //then
        assert score == 2
    }

    @Test
    void shouldScoreFourIfAllCriteriaSetAndMet() {
        //given
        Criteria criteria = new Criteria()
                                            .priceRange(25 .. 30)
                                            .attributes(["internet", "pool", "moat"])

        //when
        int score = scorer.score(accommodation, criteria)

        //then
        assert score == 4
    }
}
