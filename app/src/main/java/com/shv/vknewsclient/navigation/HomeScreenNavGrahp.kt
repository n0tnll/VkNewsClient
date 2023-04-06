package com.shv.vknewsclient.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.shv.vknewsclient.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route,
        builder = {
            composable(route = Screen.NewsFeed.route) {
                newsFeedScreenContent()
            }
            composable(
                route = Screen.Comments.route,
                arguments = listOf(
                    navArgument(name = Screen.KEY_FEED_POST) {
                        type = FeedPost.NavigationType
                    }
                )
            ) {//comments
                val feedPost = if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                    it.arguments?.getParcelable(Screen.KEY_FEED_POST, FeedPost::class.java)
                } else {
                    it.arguments?.getParcelable(Screen.KEY_FEED_POST)
                } ?: throw RuntimeException("Args is null")
                commentsScreenContent(feedPost)
            }
        }
    )
}