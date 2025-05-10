package dev.stormery.pyrkon.app.feature_Guests.domain.use_cases

import dev.stormery.pyrkon.app.feature_Guests.domain.model.Zones
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.ZonesRepository

class GetZonesListUseCase(
    private val repository: ZonesRepository
) {
    suspend operator fun invoke(): Zones {
        return repository.getZonesList()
    }
}