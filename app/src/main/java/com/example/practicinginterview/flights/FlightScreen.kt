package com.example.practicinginterview.flights

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

@Composable
fun FlightScreen() {

    val viewModel = getViewModel<FlightViewModel>()
    val flightsList = viewModel.flightList.collectAsState().value

    Column(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        FilterBar(
            { viewModel.filterFlights(it) },
            { viewModel.updateList() },
        )
        if (flightsList.isEmpty()) {
            Text("No flights under that price")
        } else {
            FlightList(
                flightsList,
            )
        }

    }
}

@Composable
private fun FlightList(
    flightList: List<Flight>,
) {
    LazyColumn {
        items(
            items = flightList,
            key = { item -> item.name }
        ) { item ->
            ListItem(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = Color.LightGray
                    ),
                headlineContent = { Text(item.name) },
                supportingContent = {
                    Text("$ ${item.price}")
                },
                trailingContent = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }

            )
        }
    }
}

@Composable
fun FilterBar(
    onClickFilter: (Int) -> Unit,
    onClickWithoutValue: () -> Unit,
) {
    var input by rememberSaveable { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        TextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier
                .weight(1f)
                .padding(end = 5.dp)
        )
        Button(
            onClick = {
                if (input.isEmpty()) onClickWithoutValue() else onClickFilter(input.toInt())
            }
        ) {
            Text("Submit")
        }
    }
}


@Preview
@Composable
fun FlightScreenPreview() {
    FlightScreen()
}