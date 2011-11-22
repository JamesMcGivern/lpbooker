package com.lonelyplanet.hotelbooker.model

import org.apache.commons.lang.builder.EqualsBuilder

/**
 *
 */
class Accommodation {

    long id
    String name = ""
    int price
    List<String> attributes = []
    int freeCapacity
    int totalCapacity

    boolean isInsufficient() {
        return freeCapacity == 0
    }

    boolean equals(Object o) {
        if (getClass() != o.class) return false

        Accommodation that = (Accommodation) o

        if (id != that.id) return false
        if (price != that.price) return false
        if (totalCapacity != that.totalCapacity) return false

        //should be able to use listA == listB but seem to have a bug so we do manual checks instead
        if(attributes.size() != that.attributes.size()) return false
        attributes.each {that.attributes.contains(it)}

        if (name != that.name) return false

        return true
    }

    int hashCode() {
        int result
        result = (int) (id ^ (id >>> 32))
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + price
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0)
        result = 31 * result + freeCapacity
        result = 31 * result + totalCapacity
        return result
    }

    String toString() {
       "{ id: $id, name: $name, price: $price, attributes: ['${attributes.join("\', '")}'], totalCapacity: $totalCapacity, freeCapacity: $freeCapacity }"
    }
}
