package dev.stormery.pyrkon.app.feature_Guests.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Zones
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetGuestsListUseCase
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetZonesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuestsListViewModel @Inject constructor(
    private val getGuestsUseCase: GetGuestsListUseCase,
    private val getZonesUseCase: GetZonesListUseCase,
):ViewModel() {

    private val _guestsList = MutableStateFlow(emptyList<Guest>())
    val guestsList = _guestsList.asStateFlow()

    private val _zonesList = MutableStateFlow(Zones(emptyList()))
    val zonesList = _zonesList.asStateFlow()

    init {
        loadGuests()
        loadZones()
    }

    fun refresh(){
        _guestsList.update {
            emptyList()
        }
        _zonesList.update {
            Zones(emptyList())
        }
        loadGuests()
        loadZones()
    }

    private fun loadGuests() {
        viewModelScope.launch {
            getGuestsUseCase().collect{ guests ->
                _guestsList.update {
                    guests
                }
            }
        }
    }

    private fun loadZones() {
        viewModelScope.launch {
            val zones = getZonesUseCase()
            _zonesList.update {
                zones
            }
        }
    }


}