package com.lonelyplanet.hotelbooker.model

/**
 *
 */
class Traveller {

    long id
    String name


    boolean equals(o) {
        if (getClass() != o.class) return false;

        Traveller traveller = (Traveller) o;

        if (id != traveller.id) return false;
        if (name != traveller.name) return false;

        return true;
    }

    int hashCode() {
        int result;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    String toString() {
        "{ id: $id, name: $name }"
    }
}
