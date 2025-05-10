package dev.stormery.pyrkon.app.feature_Guests.data.local.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.stormery.pyrkon.app.feature_Guests.data.local.dto.GuestEntity
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import kotlinx.coroutines.flow.Flow


@Dao
interface GuestDao {

    @Query("SELECT * FROM guests")
    fun getAllGuests(): Flow<List<GuestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGuests(guests: List<GuestEntity>)
}