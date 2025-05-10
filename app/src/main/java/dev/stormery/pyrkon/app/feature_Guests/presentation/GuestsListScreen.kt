package dev.stormery.pyrkon.app.feature_Guests.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
                Modifier.fillMaxWidth()
            ){
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
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
               zonesList.zones.forEach{
                   Text(text = it)
               }
            }
            LazyColumn(
            ) {
                guestsList.forEach{
                    item{
                        GuestListRowItem(it.name)
                    }
                }
            }
        }
    }
}