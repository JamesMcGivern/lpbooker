package com.lonelyplanet.hotelbooker.model

/**
 *
 */
class Criteria {

    private Range priceRange
    private List<String> attributes = []

    Criteria() {}

    Criteria priceRange(Range range) {
        this.priceRange = range
        return this
    }

    Criteria attribute(String attribute) {
        this.attributes << attribute
        return this
    }

    Criteria attributes(List<String> attributes) {
        this.attributes.addAll(attributes)
        return this
    }

    Criteria attributes(String... attributez) {
        return attributes(attributez.toList())
    }

    Range getPriceRange() {
        return priceRange
    }

    List<String> getAttributes() {
        return attributes
    }

    boolean equals(o) {
        if (this.is(o)) return true;
        if (getClass() != o.class) return false;

        Criteria criteria = (Criteria) o;

        if (attributes != criteria.attributes) return false;
        if (priceRange != criteria.priceRange) return false;

        return true;
    }

    int hashCode() {
        int result;
        result = (priceRange != null ? priceRange.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }

    String toString() {
        "{ price-range: $priceRange, requirements: $attributes }"
    }

}
