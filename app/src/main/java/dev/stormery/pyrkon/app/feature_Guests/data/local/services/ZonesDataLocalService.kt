package dev.stormery.pyrkon.app.feature_Guests.data.local.services

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Zones

// This is a service class that loads zones data from a JSON file in the assets folder.
// It's used instead API calls to provide data for the app.
class ZonesDataLocalService(private val context: Context) {
    fun loadZonesFromAssets(): Zones {
        val json = context.assets.open("zones.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<Zones>() {}.type
        return Gson().fromJson(json, type)
    }
}