package dev.stormery.pyrkon.app.feature_Guests.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Zones
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.MatchAll
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.NameContains
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.ZoneMatches
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.FilterGuestsUseCase
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetGuestsListUseCase
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetZonesListUseCase
import dev.stormery.pyrkon.app.feature_Guests.presentation.state.FilterEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuestsListViewModel @Inject constructor(
    private val getGuestsUseCase: GetGuestsListUseCase,
    private val getZonesUseCase: GetZonesListUseCase,
    private val filterGuestsUseCase: FilterGuestsUseCase,
):ViewModel() {

    private val _guestsList = MutableStateFlow(emptyList<Guest>())
    val guestsList = _guestsList.asStateFlow()

    private val _zonesList = MutableStateFlow(Zones(emptyList()))
    val zonesList = _zonesList.asStateFlow()

    private val _filteredGuestsList = MutableStateFlow(emptyList<Guest>())
    val filteredGuestsList = _filteredGuestsList.asStateFlow()


    private val _searchName = MutableStateFlow("")
    val searchName = _searchName.asStateFlow()

    private val _searchZone = MutableStateFlow("")
    val searchZone = _searchZone.asStateFlow()

    fun init() {
        loadGuests()
        loadZones()
    }

    fun onRefresh(){
        _guestsList.update {
            emptyList()
        }
        _zonesList.update {
            Zones(emptyList())
        }
        _filteredGuestsList.update {
            emptyList()
        }
        loadGuests(true)
        loadZones()
    }

    private fun loadGuests(isRefreshing:Boolean = false) {
        viewModelScope.launch {
            getGuestsUseCase(isRefreshing).collect{ guests ->
                _guestsList.update {
                    guests
                }
                _filteredGuestsList.update {
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

    fun onFilterEvent(event:FilterEvent){
        when(event){
            is FilterEvent.SearchByName -> _searchName.value = event.name
            is FilterEvent.SearchByZone -> _searchZone.value = event.zone
            is FilterEvent.ClearNameSearch -> _searchName.value = ""
            is FilterEvent.ClearZoneSearch -> _searchZone.value = ""
        }
        applyFilter()
    }

    private fun applyFilter(){
        val nameSpec = if(_searchName.value.isNotBlank()) NameContains(_searchName.value) else MatchAll
        val zoneSpec = if(_searchZone.value.isNotBlank()) ZoneMatches(_searchZone.value) else MatchAll
        val spec = nameSpec and zoneSpec
        viewModelScope.launch {
            val result = filterGuestsUseCase(_guestsList.value,spec)
            _filteredGuestsList.update {
                result
            }
        }
    }

    fun textQueryChanged(query:String){

    }


}