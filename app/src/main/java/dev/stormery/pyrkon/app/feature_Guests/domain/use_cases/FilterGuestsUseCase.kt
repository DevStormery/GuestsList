package dev.stormery.pyrkon.app.feature_Guests.domain.use_cases

import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.specification.GuestSpecification
import javax.inject.Inject

class FilterGuestsUseCase @Inject constructor() {
    operator fun invoke(guests: List<Guest>, spec: GuestSpecification): List<Guest> {
        return guests.filter { guest ->
            spec.isSatisfiedBy(guest)
        }
    }
}