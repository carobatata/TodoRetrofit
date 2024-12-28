package com.example.practicinginterview.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicinginterview.CreateTodoViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateTodoFormScreen(onClickGoToLastScreen: () -> Unit) {

    val viewModel = getViewModel<CreateTodoViewModel>()
    val todoName = viewModel.todoName.collectAsState().value
    val optionSelectedText = viewModel.todoOptions.collectAsState().value

    TodoForm(
        todoName,
        {viewModel.updateTodoName(it)},
        { viewModel.createTodo(it) },
        onClickGoToLastScreen,
        { viewModel.updateTodoOption(it) },
        optionSelectedText
    )
}

@Composable
fun TodoForm(
    todoName: String,
    updateTodoName: (String) -> Unit,
    onClickCreateToDo: (String) -> Unit,
    onClickGoToLastScreen: () -> Unit,
    updateTodoOption: (String) -> Unit,
    optionSelectedText: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Create form",
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.SemiBold
        )

        Column {
            Text(
                text = "Name",
                Modifier.padding(vertical = 10.dp))
            TextField(
                value = todoName,
                onValueChange = { updateTodoName(it) },
                placeholder = { Text("Create a Todo") },
                modifier = Modifier.fillMaxWidth(),
                isError = todoName.isEmpty(),
                supportingText = {
                    if(todoName.isEmpty()){
                        Text("Please, add a todo name")
                    }
                },
                trailingIcon = {
                    if(todoName.isEmpty()){
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "",
                        )
                    }
                }
            )
        }

        DropDownWithOptions(updateTodoOption, optionSelectedText)

        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            onClick = {
                if(todoName.isEmpty().not()){
                    onClickCreateToDo(todoName)
                    onClickGoToLastScreen()
                }
            }
        ) {
            Text("Create the Todo")
        }
    }
}

@Composable
fun DropDownWithOptions(updateTodoOption: (String) -> Unit, optionSelectedText: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(Modifier.border(
        width = 2.dp,
        color = Color.Black
    ).padding(start = 15.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(optionSelectedText)
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = ""
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            DropdownMenuItem(
                text = { Text("Option 1") },
                onClick = { updateTodoOption("Option 1") }
            )
            DropdownMenuItem(
                text = { Text("Option 2") },
                onClick = { updateTodoOption("Option 2") }
            )
        }
    }
}


@Preview
@Composable
fun CreateFormPreview() {
    TodoForm("Todo", {}, {}, {}, {}, "Options")
}