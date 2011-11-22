package com.lonelyplanet.hotelbooker.model

import org.junit.Test

/**
 *
 */
public class CriteriaTest {

    @Test
    void shouldCreateEmptyCriteria() {
        //when
        Criteria criteria = new Criteria()

        //then
        assert criteria.priceRange == null
        assert criteria.attributes == []
    }

    @Test
    void shouldCreateCriteriaWithPriceRange() {
        //given
        def range = 40 .. 50

        //when
        Criteria criteria = new Criteria().priceRange(range)

        //then
        assert criteria.priceRange == range
        assert criteria.attributes == []
    }

    @Test
    void shouldCreateCriteriaWithAttribute() {
        //given
        String attribute = "internet"

        //when
        Criteria criteria = new Criteria().attribute(attribute)

        //then
        assert criteria.priceRange == null
        assert criteria.attributes == [attribute]
    }

    @Test
    void shouldCreateCriteriaWithAttributes() {
        //given
        List<String> attributes = ["internet", "bath"]

        //when
        Criteria criteria = new Criteria().attributes(attributes)

        //then
        assert criteria.priceRange == null
        assert criteria.attributes == attributes
    }
}
