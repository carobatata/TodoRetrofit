package com.example.practicinginterview.flights

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FlightViewModel : ViewModel() {

    private val _flightList = MutableStateFlow<List<Flight>>(emptyList())
    var flightList: StateFlow<List<Flight>> = _flightList

    init {
        updateList()
    }

    private fun updateList() {
        val list = List(15) { index ->
            Flight(
                name = "Flight # ${index + 1}",
                price = (500..1500).random()
            )
        }
        _flightList.value = list
    }

}