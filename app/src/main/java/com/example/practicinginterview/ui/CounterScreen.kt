package com.example.practicinginterview.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CounterScreen() {

    var value by rememberSaveable { mutableIntStateOf(0) }

    Column(Modifier.fillMaxSize()) {

        Text(
            "Counter = $value"
        )
        Button(
            onClick = { value += 1 }
        ) {
            Text(
                text = "Increment"
            )
        }

    }

}


@Preview
@Composable
fun CounterScreenPreview(){
    CounterScreen()
}