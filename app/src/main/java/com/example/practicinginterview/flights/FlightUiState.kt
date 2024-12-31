package com.example.practicinginterview.flights

sealed class FlightUiState {
    data class Success(val flightList: List<Flight>): FlightUiState()
    data object Error: FlightUiState()
    data object Loading: FlightUiState()
}