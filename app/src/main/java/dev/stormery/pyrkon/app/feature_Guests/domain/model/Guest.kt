package dev.stormery.pyrkon.app.feature_Guests.domain.model


data class Guest(
    val name: String,
    val summary: String,
    val imageURL: String,
    val zones: List<String>
)