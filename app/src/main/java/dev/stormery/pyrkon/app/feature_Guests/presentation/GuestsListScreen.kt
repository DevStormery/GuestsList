package dev.stormery.pyrkon.app.feature_Guests.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.stormery.pyrkon.app.R
import dev.stormery.pyrkon.app.feature_Guests.presentation.components.GuestListRowItem
import dev.stormery.pyrkon.app.navigation.components.TopBar
import dev.stormery.pyrkon.app.ui.components.DropdownMenu

@Composable
fun GuestsListScreen(
) {
    val viewModel = hiltViewModel<GuestsListViewModel>()
    val guestsList = viewModel.guestsList.collectAsState().value
    val zonesList = viewModel.zonesList.collectAsState().value

    Scaffold(
        topBar = {
            TopBar(stringResource(R.string.guests_list))
        }
    ) { padding->
        Box(
            modifier = Modifier.padding(padding).fillMaxSize().background(color = MaterialTheme.colorScheme.background)
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
                        modifier = Modifier.fillMaxWidth(0.4f).clip(RoundedCornerShape(15.dp)).background(MaterialTheme.colorScheme.background),
                        onItemSelected = { zone ->
                            //viewModel.onEvent(GuestsListEvent.OnZoneSelected(zone))
                        },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextField(
                        value = "",
                        onValueChange = {},
                        label = { Text(text = "Search") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            backgroundColor = MaterialTheme.colorScheme.surface,
                        )
                    )
                }
                LazyColumn(
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
}