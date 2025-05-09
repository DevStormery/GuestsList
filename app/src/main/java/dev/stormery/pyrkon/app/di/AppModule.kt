package dev.stormery.pyrkon.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.stormery.pyrkon.app.database.AppDatabase
import dev.stormery.pyrkon.app.feature_Guests.data.local.GuestDataLocalService
import dev.stormery.pyrkon.app.feature_Guests.data.local.data_source.GuestDao
import dev.stormery.pyrkon.app.feature_Guests.data.repository.GuestRepositoryImpl
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.GuestRepository
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetGuestsListUseCase
import dev.stormery.pyrkon.app.feature_Guests.presentation.GuestsListViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideGuestDao(
        @ApplicationContext context: Context
    ): GuestDao {
        return AppDatabase.getInstance(context)!!.guestDao()
    }

    @Provides
    @Singleton
    fun provideGuestDataLocalService(
        @ApplicationContext context: Context,
    ): GuestDataLocalService {
        return GuestDataLocalService(context)
    }

    @Provides
    @Singleton
    fun provideGuestRepository(
        @ApplicationContext context: Context,
    ):GuestRepository{
        return GuestRepositoryImpl(
            provideGuestDataLocalService(context)
        )
    }

    @Provides
    @Singleton
    fun provideGetGuestsListUseCase(
        @ApplicationContext context: Context,
    ): GetGuestsListUseCase {
        return GetGuestsListUseCase(
            provideGuestRepository(context)
        )
    }

    @Provides
    @Singleton
    fun provideGuestsListViewModel(
        @ApplicationContext context: Context,
    ): GuestsListViewModel {
        return GuestsListViewModel(
            provideGetGuestsListUseCase(context)
        )
    }

}