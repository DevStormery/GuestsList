package dev.stormery.pyrkon.app.feature_Guests.data.repository

import dev.stormery.pyrkon.app.feature_Guests.data.local.services.ZonesDataLocalService
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Zones
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.ZonesRepository

class ZonesRepositoryImpl(
    private val zonesService: ZonesDataLocalService,
) : ZonesRepository {

    private var cachedZones: Zones? = null

    override suspend fun fetchZonesFromService(): Zones {
        return zonesService.loadZonesFromAssets()
    }

    override suspend fun getZonesList(): Zones {
        return cachedZones ?: fetchZonesFromService().also {
            cachedZones = it
        }
    }
}