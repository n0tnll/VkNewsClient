package com.shv.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shv.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test()
        }
    }
}


@Composable
private fun Test() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Example3()
    }
}

@Composable
private fun Example1() {
    OutlinedButton(onClick = { /*TODO*/ }) {
        Text(text = "Hello, World!")
    }
}

@Composable
private fun Example2() {
    TextField(
        value = "Value",
        onValueChange = { },
        label = { Text(text = "Label") }
    )
}

@Composable
private fun Example3() {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = {
            Text(text = "Are you sure?")
        },
        text = {
            Text(text = "Do you want to delete this file?")
        },
        confirmButton = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Yes",
                    color = MaterialTheme.colors.onSecondary
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "No",
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    )
}