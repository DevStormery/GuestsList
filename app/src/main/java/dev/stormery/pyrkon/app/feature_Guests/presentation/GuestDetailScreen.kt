package dev.stormery.pyrkon.app.feature_Guests.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.stormery.pyrkon.app.R
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.navigation.components.TopBar
import dev.stormery.pyrkon.app.ui.components.RectangularImage

@Composable
fun GuestDetailScreen(
    guestName:String,
    onBackPressed: () -> Unit
) {
    val viewModel = hiltViewModel<GuestsListViewModel>()
    val guest = viewModel.guestsList.collectAsState().value
        .firstOrNull { it.name == guestName } ?: Guest(
        name = "",
        imageURL = "",
        summary = "",
        zones = emptyList()
    )
Scaffold(
    topBar = {
        TopBar(guestName,true,onBackPressed = onBackPressed)
    }
) { padding->
    Column(
        Modifier.fillMaxSize()
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            item{
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    RectangularImage(guest.imageURL)
                }

                Column(Modifier.fillMaxWidth().padding(16.dp)) {
                    Row(
                        Modifier.padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${stringResource(R.string.zone)}: ",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 3.dp),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = guest.zones.joinToString(", "),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    Text(
                        text = guest.summary,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}
}