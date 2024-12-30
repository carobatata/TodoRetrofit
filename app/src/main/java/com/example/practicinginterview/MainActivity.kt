package com.example.practicinginterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.practicinginterview.flights.FlightScreen
import com.example.practicinginterview.ui.CreateTodoScreen
import com.example.practicinginterview.ui.CreateTodoForm
import com.example.practicinginterview.ui.FlightScreenRoute
import com.example.practicinginterview.ui.TodoListScreen
import com.example.practicinginterview.ui.screen.CreateTodoFormScreen
import com.example.practicinginterview.ui.screen.CreateTodoUiScreen
import com.example.practicinginterview.ui.screen.TodoList
import com.example.practicinginterview.ui.theme.PracticingInterviewTheme
import com.example.practicinginterview.ui.theme.Purple40
import com.example.practicinginterview.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticingInterviewTheme {
                //to navigate from screen to screen
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Todo App") },
                            colors = topAppBarColors(
                                containerColor = Purple40,
                                titleContentColor = PurpleGrey80,
                            ),
                            actions = {
                                IconButton(
                                    onClick = {
                                        navController.navigate(
                                            CreateTodoScreen(
                                                "Carolina"
                                            )
                                        )
                                    },
                                    colors = IconButtonColors(
                                        containerColor = PurpleGrey80,
                                        contentColor = Purple40,
                                        disabledContentColor = Color.Unspecified,
                                        disabledContainerColor = Color.Unspecified
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "",
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = TodoListScreen
                        ) {
                            composable<TodoListScreen> { TodoList() }
                            composable<CreateTodoScreen> {
                                val args = it.toRoute<CreateTodoScreen>()
                                CreateTodoUiScreen(args.name,
                                    { navController.navigate(CreateTodoForm) },
                                    {navController.popBackStack() },
                                    { navController.navigate(FlightScreenRoute) }
                                )
                            }
                            composable<CreateTodoForm> { CreateTodoFormScreen { navController.popBackStack() } }
                            composable<FlightScreenRoute> { FlightScreen() }
                        }
                    }
                }
            }
        }
    }
}