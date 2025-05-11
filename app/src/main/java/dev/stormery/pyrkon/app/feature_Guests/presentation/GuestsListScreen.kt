package dev.stormery.pyrkon.app.feature_Guests.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.stormery.pyrkon.app.feature_Guests.presentation.components.GuestListRowItem
import dev.stormery.pyrkon.app.ui.components.DropdownMenu

@Composable
fun GuestsListScreen(
) {
    val viewModel = hiltViewModel<GuestsListViewModel>()
    val guestsList = viewModel.guestsList.collectAsState().value
    val zonesList = viewModel.zonesList.collectAsState().value


    Box(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)
    ){
        Column(
            Modifier.fillMaxSize(),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ){
                DropdownMenu(
                    zonesList.zones,
                    modifier = Modifier.fillMaxWidth(0.4f).clip(RoundedCornerShape(15.dp)).background(MaterialTheme.colorScheme.surface),
                    onItemSelected = { zone ->
                        //viewModel.onEvent(GuestsListEvent.OnZoneSelected(zone))
                    },
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Search") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                guestsList.forEach{
                    item{
                        GuestListRowItem(it)
                    }
                    item{
                        Divider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = MaterialTheme.colorScheme.surface,
                            thickness = 1.dp,
                        )
                    }
                }
            }
        }
    }
}