package dev.stormery.pyrkon.app.feature_Guests.data.repository

import android.util.Log
import dev.stormery.pyrkon.app.feature_Guests.data.local.services.GuestDataLocalService
import dev.stormery.pyrkon.app.feature_Guests.data.local.data_source.GuestDao
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.model.toGuestData
import dev.stormery.pyrkon.app.feature_Guests.domain.model.toGuestEntity
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.GuestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GuestRepositoryImpl(
    private val service : GuestDataLocalService, // In future, this can be a remote data source
    private val dao: GuestDao
):GuestRepository  {
    private val TAG = GuestRepositoryImpl::class.java.simpleName

    override suspend fun fetchGuestsFromService(): List<Guest> {
        return service.loadGuestsFromAssets()
    }

    override fun getGuestsList(isRefreshing:Boolean): Flow<List<Guest>> = flow {
        try {
            val localData = dao.getAllGuests().firstOrNull()

            if (localData.isNullOrEmpty() || isRefreshing) {
                val guests = fetchGuestsFromService()
                Log.d(TAG, "Fetching guests from remote service: $guests")
                dao.insertGuests(guests.map { it.toGuestEntity() })
            }
            delay(3000) // Simulate network delay to show loading state
            dao.getAllGuests()
                .map { list -> list.map { it.toGuestData() } }
                .collect { emit(it) }

        } catch (e: Exception) {
            Log.e(TAG, "Error fetching guests", e)
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)
}