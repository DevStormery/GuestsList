package dev.stormery.pyrkon.app.feature_Guests.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "guests")
data class GuestEntity(
    @PrimaryKey val name:String,
    val summary:String,
    val imageUrl:String,
    val zones:String
)

