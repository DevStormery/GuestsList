package dev.stormery.pyrkon.app.feature_Guests.domain.repository

import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.specification.GuestSpecification

class NameContains(private val query:String): GuestSpecification {
    override fun isSatisfiedBy(guest: Guest): Boolean {
        return guest.name.contains(query, ignoreCase = true)
    }
}

class ZoneMatches(private val zone:String): GuestSpecification {
    override fun isSatisfiedBy(guest: Guest): Boolean {
        return guest.zones.any { it.equals(zone, ignoreCase = true) }
    }
}

object MatchAll: GuestSpecification {
    override fun isSatisfiedBy(guest: Guest): Boolean = true
}