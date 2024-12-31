package com.example.practicinginterview

import com.example.practicinginterview.flights.Flight
import com.example.practicinginterview.flights.FlightViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FlightViewModelTest {

    private lateinit var sut: FlightViewModel

    @Before
    fun setUp() {
        sut = FlightViewModel()
    }

    @Test
    fun `given a max price then it should only return the ones below that`() {
        // Given
        val flightBelowPrice = Flight(
            name = "Flight2",
            price = 799
        )
        val flightHigherPrice = Flight(
            name = "Flight1",
            price = 801
        )

        // When
        val result = sut.filterFlightsUntilValue(listOf(flightBelowPrice, flightHigherPrice), 800)

        // Then
        val expectedFlights = listOf(flightBelowPrice)
        assertEquals(expectedFlights, result)
    }
}