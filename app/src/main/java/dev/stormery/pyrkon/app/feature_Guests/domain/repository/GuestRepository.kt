package dev.stormery.pyrkon.app.feature_Guests.domain.repository

import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest

interface GuestRepository {
    suspend fun getGuests(): List<Guest>
}