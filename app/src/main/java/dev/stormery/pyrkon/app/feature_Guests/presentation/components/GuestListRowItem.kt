package dev.stormery.pyrkon.app.feature_Guests.presentation.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.stormery.pyrkon.app.R
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.navigation.NavigationUtils
import dev.stormery.pyrkon.app.ui.components.RectangularImage

@Composable
fun GuestListRowItem(
    guest: Guest
) {
    val guestImageSize = 100.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(guestImageSize)
            .padding(16.dp,8.dp)
            .clickable {
                NavigationUtils.navigateToGuestDetail(guest.name)
            },

    ){
        Box(Modifier.size(guestImageSize)){
            RectangularImage(guest.imageURL, modifier = Modifier.clip(RoundedCornerShape(15.dp)))
        }
        Box(
            Modifier.fillMaxHeight().padding(horizontal = 8.dp),
        ){
            Text(
                text = guest.name,
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                fontWeight = FontWeight.Bold,
                lineHeight = MaterialTheme.typography.labelLarge.lineHeight,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Text(
                text = "${stringResource(R.string.zone)}: ${guest.zones.joinToString(", ")}",
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                modifier = Modifier.align(Alignment.BottomStart),
                style = MaterialTheme.typography.labelSmall.copy(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
    }
}

@Preview
@Composable
fun GuestItemPreview(){
    GuestListRowItem(
        guest = Guest(
            name = "John Doe",
            imageURL = "https://example.com/image.jpg",
            zones = listOf("Zone 1", "Zone 2"),
            summary = "",
        )
    )
}