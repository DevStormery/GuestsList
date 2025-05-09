package dev.stormery.pyrkon.app.feature_Guests.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetGuestsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuestsListViewModel @Inject constructor(
    private val getGuestsUseCase: GetGuestsListUseCase,
):ViewModel() {

    private val _guestsList = MutableStateFlow(emptyList<Guest>())
    val guestsList = _guestsList.asStateFlow()

    init {
        loadGuests()
    }

    fun loadGuests() {
        viewModelScope.launch {
            val guest = getGuestsUseCase()
            _guestsList.update {
                guest
            }
        }
    }
}