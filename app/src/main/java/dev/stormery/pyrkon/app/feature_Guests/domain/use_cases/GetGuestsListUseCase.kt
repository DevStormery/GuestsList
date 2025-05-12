package dev.stormery.pyrkon.app.feature_Guests.domain.use_cases

import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.GuestRepository
import kotlinx.coroutines.flow.Flow

class GetGuestsListUseCase(
    private val repository: GuestRepository
) {
    operator fun invoke(isRefreshing:Boolean = false) : Flow<List<Guest>> = repository.getGuestsList(isRefreshing)
}