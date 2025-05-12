package dev.stormery.pyrkon.app.feature_Guests.presentation.state


sealed class FilterEvent {
    data class SearchByName(val name: String) : FilterEvent()
    data class SearchByZone(val zone: String) : FilterEvent()
    object ClearNameSearch : FilterEvent()
    object ClearZoneSearch : FilterEvent()
}