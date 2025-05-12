package dev.stormery.pyrkon.app.feature_Guests.domain.repository

import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import kotlinx.coroutines.flow.Flow

interface GuestRepository {
    suspend fun fetchGuestsFromService(): List<Guest>
    fun getGuestsList(isRefreshing:Boolean = false): Flow<List<Guest>>
}