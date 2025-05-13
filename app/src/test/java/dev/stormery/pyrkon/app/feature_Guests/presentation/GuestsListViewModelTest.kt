package dev.stormery.pyrkon.app.feature_Guests.presentation

import app.cash.turbine.test
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.model.Zones
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.FilterGuestsUseCase
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetGuestsListUseCase
import dev.stormery.pyrkon.app.feature_Guests.domain.use_cases.GetZonesListUseCase
import dev.stormery.pyrkon.app.feature_Guests.presentation.state.FilterEvent
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test




@OptIn(ExperimentalCoroutinesApi::class)
class GuestsListViewModelTest {

    private lateinit var viewModel: GuestsListViewModel
    private val getGuestsListUseCase: GetGuestsListUseCase = mockk()
    private val getZonesListUseCase: GetZonesListUseCase = mockk()
    private val filterGuestsUseCase: FilterGuestsUseCase = mockk()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val fakeGuests = listOf(
        Guest(name = "Anna", summary = "Test guest", imageURL = "", zones = listOf("A")),
        Guest(name = "Bob", summary = "Other", imageURL = "", zones = listOf("B"))
    )

    private val fakeZones = Zones(listOf("A", "B", "C"))

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getGuestsListUseCase(any()) } returns flowOf(fakeGuests)
        coEvery { getZonesListUseCase() } returns fakeZones
        coEvery { filterGuestsUseCase(any(), any()) } answers { firstArg() }
        viewModel = GuestsListViewModel(getGuestsListUseCase, getZonesListUseCase, filterGuestsUseCase)
        viewModel.init()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial load fetches guests and zones`() = runTest {

        viewModel.guestsList.test {
            val guests = awaitItem()
            assertEquals(fakeGuests, guests)
        }

        viewModel.zonesList.test {
            val zones = awaitItem()
            assertEquals(fakeZones, zones)
        }
    }

    @Test
    fun `onFilterEvent with name filters correctly`() = runTest {
        coEvery { filterGuestsUseCase(any(), any()) } returns listOf(fakeGuests[0])

        viewModel.onFilterEvent(FilterEvent.SearchByName("Anna"))

        viewModel.filteredGuestsList.test {
            val filtered = awaitItem()
            assertEquals(listOf(fakeGuests[0]), filtered)
        }
    }

    @Test
    fun `onFilterEvent with clear resets filters`() = runTest {
        viewModel.onFilterEvent(FilterEvent.ClearNameSearch)
        viewModel.onFilterEvent(FilterEvent.ClearZoneSearch)
        viewModel.filteredGuestsList.test {
            val filtered = awaitItem()
            assertEquals(fakeGuests, filtered)
        }
    }
}


