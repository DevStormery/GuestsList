package dev.stormery.pyrkon.app.feature_Guests.data.local.data_source

import androidx.room.Dao
import androidx.room.Query
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest


@Dao
interface GuestDao {

//    @Query("SELECT * FROM guests")
//    suspend fun getAllGuests(): List<Guest>
}