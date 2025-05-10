package dev.stormery.pyrkon.app.feature_Guests.data.local.services

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest



// This is a service class that loads guest data from a JSON file in the assets folder.
// It's used instead API calls to provide data for the app.
class GuestDataLocalService(private val context:Context) {
    fun loadGuestsFromAssets(): List<Guest> {
        val json = context.assets.open("guests.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Guest>>() {}.type
        return Gson().fromJson(json, type)
    }
}