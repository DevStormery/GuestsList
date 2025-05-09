package dev.stormery.pyrkon.app.feature_Guests.data.repository

import dev.stormery.pyrkon.app.feature_Guests.data.local.GuestDataLocalService
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.GuestRepository

class GuestRepositoryImpl(
    private val service : GuestDataLocalService, // In future, this can be a remote data source
):GuestRepository  {
    override suspend fun getGuests(): List<Guest> {
        return service.loadGuestsFromAssets("guests.json")
    }
}