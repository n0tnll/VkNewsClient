package com.shv.vknewsclient.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.shv.vknewsclient.MainViewModel
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val selectedNavItem by viewModel.selectedNavItem.observeAsState(NavigationItem.Home)

    Scaffold(
        bottomBar = {
            BottomNavigation {

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )

                items.forEach { navigationItem ->
                    BottomNavigationItem(
                        icon = {
                            Icon(navigationItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = navigationItem.titleResId))
                        },
                        selected = selectedNavItem == navigationItem,
                        onClick = { viewModel.selectNavItem(navigationItem) },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedNavItem) {
            NavigationItem.Home -> {
                HomeScreen(paddingValues = paddingValues, viewModel = viewModel)
            }
            NavigationItem.Favorite -> {
                TextCounter(name = "Favorite screen")
            }
            NavigationItem.Profile -> {
                TextCounter(name = "Profile screen")
            }
        }
    }
}

@Composable
private fun TextCounter(name: String) {
    var count by remember {
        mutableStateOf(0)
    }
    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count"
    )
}