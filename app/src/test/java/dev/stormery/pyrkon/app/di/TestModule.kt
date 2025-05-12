package dev.stormery.pyrkon.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.GuestRepository
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.ZonesRepository
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.FilterGuestsUseCase
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetGuestsListUseCase
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetZonesListUseCase
import org.mockito.Mockito.mock
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Singleton
    fun provideGuestRepository(): GuestRepository =
        mock()

    @Provides
    @Singleton
    fun provideZonesRepository(): ZonesRepository =
        mock()

    @Provides
    @Singleton
    fun provideGetGuestsListUseCase(
        guestRepository: GuestRepository
    ): GetGuestsListUseCase = GetGuestsListUseCase(guestRepository)

    @Provides
    @Singleton
    fun provideGetZonesListUseCase(
        zonesRepository: ZonesRepository
    ): GetZonesListUseCase = GetZonesListUseCase(zonesRepository)

    @Provides
    @Singleton
    fun provideFilterGuestsUseCase(): FilterGuestsUseCase = FilterGuestsUseCase()


}
