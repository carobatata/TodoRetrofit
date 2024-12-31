package com.example.practicinginterview.flights

import androidx.annotation.OpenForTesting
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FlightViewModel : ViewModel() {

    private val _flightList = MutableStateFlow<List<Flight>>(emptyList())
    var flightList: StateFlow<List<Flight>> = _flightList

    init {
        updateList()
    }

    fun filterFlights(maxPrice: Int)  {
        updateList()
        _flightList.value = filterFlightsUntilValue(_flightList.value, maxPrice)
    }

    fun updateList() {
        val list = List(15) { index ->
            Flight(
                name = "Flight # ${index + 1}",
                price = (500..1500).random()
            )
        }
        _flightList.value = list
    }

    @OpenForTesting
    fun filterFlightsUntilValue(flights: List<Flight>, value: Int): List<Flight> {
        return flights.filter {
            it.price < value
        }
    }

}