package dev.stormery.pyrkon.app.feature_Guests.presentation.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.ui.components.RectangularImage

@Composable
fun GuestListRowItem(
    guest: Guest
) {
    val guestImageSize = 120.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp)),
    ){
        Box(Modifier.size(guestImageSize)){
            RectangularImage(guest.imageURL)
        }
        Text(text = guest.name, modifier = Modifier.padding(16.dp))
    }
}