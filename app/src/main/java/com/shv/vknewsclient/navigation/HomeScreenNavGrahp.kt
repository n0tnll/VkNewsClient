package com.shv.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
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
                    navArgument(name = Screen.KEY_FEED_POST_ID) {
                        type = NavType.IntType
                    },
                    navArgument(name = Screen.KEY_CONTENT_TEXT) {
                        type = NavType.StringType
                    }
                )
            ) {//comments
                val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
                val feedPostText = it.arguments?.getString(Screen.KEY_CONTENT_TEXT) ?: "wtf"
                commentsScreenContent(
                    FeedPost(
                        id = feedPostId,
                        contentText = feedPostText
                        )
                )
            }
        }
    )
}