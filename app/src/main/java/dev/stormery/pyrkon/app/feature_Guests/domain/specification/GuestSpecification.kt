package dev.stormery.pyrkon.app.feature_Guests.domain.specification

import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest

fun interface GuestSpecification {
    fun isSatisfiedBy(guest: Guest): Boolean

    infix fun and(other: GuestSpecification): GuestSpecification {
        return GuestSpecification { guest ->
            this.isSatisfiedBy(guest) && other.isSatisfiedBy(guest)
        }
    }
}

