package com.shv.vknewsclient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test()
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun Test() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top AppBar Title")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            val items = listOf("One", "Two", "Three")

            BottomNavigation() {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = {
                               Icon(Icons.Filled.Email, contentDescription = null)
                        },
                        label = { Text(text = item) },
                        selected = true,
                        onClick = { /*TODO*/ }
                    )
                }
            }
        },
        drawerContent = {
            Text(text = "Text1")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Text2")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Text3")
        }
    ) {
        Text(
            modifier = Modifier.padding(it),
            text = "This is scaffold content"
        )
    }
}