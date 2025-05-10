package dev.stormery.pyrkon.app.feature_Guests.domain.repository

import dev.stormery.pyrkon.app.feature_Guests.domain.model.Zones

interface ZonesRepository {
    suspend fun fetchZonesFromService(): Zones
    suspend fun getZonesList(): Zones
}