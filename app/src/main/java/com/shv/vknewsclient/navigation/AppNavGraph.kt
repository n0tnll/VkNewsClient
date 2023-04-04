package com.shv.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    favouriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        builder = {
            homeScreenNavGraph(
                newsFeedScreenContent = newsFeedScreenContent,
                commentsScreenContent = commentsScreenContent
            )
            composable(route = Screen.Favourite.route) {
                favouriteScreenContent()
            }
            composable(route = Screen.Profile.route) {
                profileScreenContent()
            }
        }
    )
}