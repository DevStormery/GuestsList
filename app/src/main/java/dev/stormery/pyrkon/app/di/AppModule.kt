package dev.stormery.pyrkon.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.stormery.pyrkon.app.database.AppDatabase
import dev.stormery.pyrkon.app.feature_Guests.data.local.services.GuestDataLocalService
import dev.stormery.pyrkon.app.feature_Guests.data.local.data_source.GuestDao
import dev.stormery.pyrkon.app.feature_Guests.data.local.services.ZonesDataLocalService
import dev.stormery.pyrkon.app.feature_Guests.data.repository.GuestRepositoryImpl
import dev.stormery.pyrkon.app.feature_Guests.data.repository.ZonesRepositoryImpl
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.GuestRepository
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.ZonesRepository
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.FilterGuestsUseCase
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetGuestsListUseCase
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetZonesListUseCase
import dev.stormery.pyrkon.app.feature_Guests.presentation.GuestsListViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//region Dao
    @Provides
    @Singleton
    fun provideGuestDao(
        @ApplicationContext context: Context
    ): GuestDao {
        return AppDatabase.getInstance(context)!!.guestDao()
    }
//endregion

//region Services
    @Provides
    @Singleton
    fun provideGuestDataLocalService(
        @ApplicationContext context: Context,
    ): GuestDataLocalService {
        return GuestDataLocalService(context)
    }

    @Provides
    @Singleton
    fun provideZonesDataLocalService(
        @ApplicationContext context: Context,
    ): ZonesDataLocalService {
        return ZonesDataLocalService(context)
    }
//endregion

//region Repositories
    @Provides
    @Singleton
    fun provideGuestRepository(
        @ApplicationContext context: Context,
    ):GuestRepository{
        return GuestRepositoryImpl(
            provideGuestDataLocalService(context),
            provideGuestDao(context)
        )
    }

    @Provides
    @Singleton
    fun provideZonesRepository(
        @ApplicationContext context: Context,
    ):ZonesRepository{
        return ZonesRepositoryImpl(
            provideZonesDataLocalService(context)
        )
    }
//endregion

//region UseCases
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
    fun provideGetZonesListUseCase(
        @ApplicationContext context: Context,
    ): GetZonesListUseCase {
        return GetZonesListUseCase(
            provideZonesRepository(context)
        )
    }

    @Provides
    @Singleton
    fun provideFilterGuestsUseCase(
    ): FilterGuestsUseCase {
        return FilterGuestsUseCase()
    }

//endregion

//region ViewModels
    @Provides
    @Singleton
    fun provideGuestsListViewModel(
        @ApplicationContext context: Context,
    ): GuestsListViewModel {
        return GuestsListViewModel(
            provideGetGuestsListUseCase(context),
            provideGetZonesListUseCase(context),
            provideFilterGuestsUseCase()
        )
    }
//endregion
}