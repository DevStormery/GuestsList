package dev.stormery.pyrkon.app.feature_Guests.data.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest




class GuestDataLocalService(private val context:Context) {
    fun loadGuestsFromAssets(fileName: String): List<Guest> {
        val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Guest>>() {}.type
        return Gson().fromJson(json, type)
    }
}