package com.shv.vknewsclient.ui.theme

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            BottomNavigation() {
                Log.d("COMPOSE_TEST", "BottomNavigation")
                val selectedItemPosition = remember {
                    mutableStateOf(0)
                }
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )

                items.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        icon = {
                            Icon(navigationItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = navigationItem.titleResId))
                        },
                        selected = selectedItemPosition.value == index,
                        onClick = { selectedItemPosition.value = index },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) {

    }
}