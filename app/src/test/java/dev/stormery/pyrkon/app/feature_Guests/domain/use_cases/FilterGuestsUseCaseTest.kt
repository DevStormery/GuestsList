package dev.stormery.pyrkon.app.feature_Guests.domain.use_cases

import dev.stormery.pyrkon.app.feature_Guests.domain.model.Guest
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.MatchAll
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.NameContains
import dev.stormery.pyrkon.app.feature_Guests.domain.repository.ZoneMatches
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FilterGuestsUseCaseTest {

    private val filterGuestsUseCase = FilterGuestsUseCase()

    // Sample data for testing
    private val testGuests = listOf(
        Guest(
            name = "John Doe",
            summary = "Author of sci-fi novels",
            imageURL = "http://example.com/john.jpg",
            zones = listOf("literacka", "filmowa")
        ),
        Guest(
            name = "Jane Smith",
            summary = "Comic book artist",
            imageURL = "http://example.com/jane.jpg",
            zones = listOf("komiksowa")
        ),
        Guest(
            name = "Alice Johnson",
            summary = "Science communicator",
            imageURL = "http://example.com/alice.jpg",
            zones = listOf("naukowa", "cosplay")
        ),
        Guest(
            name = "Bob Builder",
            summary = "Film director",
            imageURL = "http://example.com/bob.jpg",
            zones = listOf("filmowa")
        ),
        Guest(
            name = "Charlie Brown",
            summary = "Unknown",
            imageURL = "http://example.com/charlie.jpg",
            zones = emptyList()
        )
    )

    @Test
    fun `filter with MatchAll should return all guests`() {
        val result = filterGuestsUseCase(testGuests, MatchAll)
        assertEquals(testGuests.size, result.size)
        assertEquals(testGuests, result)
    }

    @Test
    fun `filter with NameContains should return matching guests`() {
        val spec = NameContains("john")
        val result = filterGuestsUseCase(testGuests, spec)

        assertEquals(2, result.size)
        assertTrue(result.any { it.name == "John Doe" })
        assertTrue(result.any { it.name == "Alice Johnson" })
    }

    @Test
    fun `filter with NameContains should be case insensitive`() {
        val spec = NameContains("JOHN")
        val result = filterGuestsUseCase(testGuests, spec)

        assertEquals(2, result.size)
        assertTrue(result.any { it.name == "John Doe" })
        assertTrue(result.any { it.name == "Alice Johnson" })
    }

    @Test
    fun `filter with ZoneMatches should return guests in specified zone`() {
        val spec = ZoneMatches("filmowa")
        val result = filterGuestsUseCase(testGuests, spec)

        assertEquals(2, result.size)
        assertTrue(result.any { it.name == "John Doe" })
        assertTrue(result.any { it.name == "Bob Builder" })
    }

    @Test
    fun `filter with ZoneMatches should be case insensitive`() {
        val spec = ZoneMatches("FILMOWA")
        val result = filterGuestsUseCase(testGuests, spec)

        assertEquals(2, result.size)
        assertTrue(result.any { it.name == "John Doe" })
        assertTrue(result.any { it.name == "Bob Builder" })
    }

    @Test
    fun `filter with combined specs using AND should return intersection`() {
        val nameSpec = NameContains("o")
        val zoneSpec = ZoneMatches("filmowa")
        val combinedSpec = nameSpec and zoneSpec

        val result = filterGuestsUseCase(testGuests, combinedSpec)

        assertEquals(2, result.size)
        assertEquals("John Doe", result[0].name) // Only John matches both
    }


}