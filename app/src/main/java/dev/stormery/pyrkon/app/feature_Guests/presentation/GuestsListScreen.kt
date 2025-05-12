package dev.stormery.pyrkon.app.feature_Guests.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import dev.stormery.pyrkon.app.R
import dev.stormery.pyrkon.app.feature_Guests.presentation.components.GuestListRowItem
import dev.stormery.pyrkon.app.feature_Guests.presentation.state.FilterEvent
import dev.stormery.pyrkon.app.navigation.components.TopBar
import dev.stormery.pyrkon.app.ui.components.DropdownMenu
import dev.stormery.pyrkon.app.ui.components.SearchBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GuestsListScreen(
    viewModel:GuestsListViewModel = hiltViewModel<GuestsListViewModel>()
) {
    val guestsList = viewModel.guestsList.collectAsState().value
    val guestsListPreview = viewModel.filteredGuestsList.collectAsState().value
    val zonesList = viewModel.zonesList.collectAsState().value
    val searchQuery = remember { mutableStateOf("") }
    val allZonesText = stringResource(R.string.all_zones)
    val selectedZone = remember { mutableStateOf(allZonesText) }


    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    fun refresh() {
        refreshing = true
        viewModel.onRefresh()
        searchQuery.value = ""
        viewModel.onFilterEvent(FilterEvent.ClearNameSearch)
        selectedZone.value = allZonesText
        viewModel.onFilterEvent(FilterEvent.ClearZoneSearch)
        refreshScope.launch {
            refreshing = false
        }
    }
    val pullRefreshState = rememberPullRefreshState(refreshing, ::refresh)

    Scaffold(
        topBar = {
            TopBar(stringResource(R.string.guests_list))
        }
    ) { padding->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .pullRefresh(pullRefreshState)
        ){
            Column(
                Modifier.fillMaxSize().padding(8.dp),
            ) {
                Row(
                    Modifier.fillMaxWidth().padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    DropdownMenu(
                        zonesList.zones,
                        selectedZone.value,
                        modifier = Modifier.fillMaxWidth(0.4f).clip(RoundedCornerShape(15.dp)).background(MaterialTheme.colorScheme.background),
                        onItemSelected = { zone ->
                            selectedZone.value = zone
                            if(zone == allZonesText) {
                                viewModel.onFilterEvent(FilterEvent.ClearZoneSearch)
                            }else{
                                viewModel.onFilterEvent(
                                    FilterEvent.SearchByZone(zone)
                                )
                            }
                        },


                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    SearchBar(stringResource(R.string.search_guest),searchQuery.value){
                        searchQuery.value = it
                        if(it.isEmpty()){
                            viewModel.onFilterEvent(FilterEvent.ClearNameSearch)
                        }else{
                            viewModel.onFilterEvent(
                                FilterEvent.SearchByName(it)
                            )
                        }
                    }

                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    when{
                        guestsListPreview.isNotEmpty() ->{
                            guestsListPreview.forEach{
                                item{
                                    GuestListRowItem(it)
                                }
                                item{
                                    HorizontalDivider(
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                        thickness = 1.dp,
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                }
                            }
                        }
                        //Loading
                        guestsList.isEmpty() ->{
                            item{
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ){
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.padding(16.dp),
                                    )
                                }
                            }
                        }
                        guestsListPreview.isEmpty() ->{
                            item{
                                val noGuestsText = when{
                                    searchQuery.value.isNotEmpty() -> stringResource(R.string.no_guests_found, searchQuery.value)
                                    zonesList.zones.isNotEmpty() -> stringResource(R.string.no_guests_in_zone,selectedZone.value)
                                    else -> stringResource(R.string.no_guests)
                                }
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ){

                                    Text(
                                        text = noGuestsText,
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(16.dp),
                                    )
                                }
                            }
                        }
                    }
                }
            }

            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}