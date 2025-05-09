package dev.stormery.pyrkon.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.stormery.pyrkon.app.feature_Guests.data.local.data_source.GuestDao
import dev.stormery.pyrkon.app.feature_Guests.data.local.dto.GuestEntity

@Database(
    entities = [GuestEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun guestDao(): GuestDao

    companion object {
        const val DATABASE_NAME = "pyrkon_database"

        @Volatile
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : AppDatabase?{
            if(instance == null){
                instance = create(context)
            }
            return instance
        }

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

    }
}