package dev.stormery.pyrkon.app.feature_Guests.domain.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.stormery.pyrkon.app.feature_Guests.data.local.dto.GuestEntity


data class Guest(
    val name: String,
    val summary: String,
    val imageURL: String,
    val zones: List<String>
)

fun GuestEntity.toGuestData(): Guest = Guest(
    name = name,
    summary = summary,
    imageURL = imageUrl,
    zones = Gson().fromJson(zones, object : TypeToken<List<String>>() {}.type)
)

fun Guest.toGuestEntity(): GuestEntity = GuestEntity(
    name = name,
    summary = summary,
    imageUrl = imageURL,
    zones = Gson().toJson(zones)
)