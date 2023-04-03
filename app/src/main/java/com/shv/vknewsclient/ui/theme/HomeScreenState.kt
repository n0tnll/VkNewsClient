package com.shv.vknewsclient.ui.theme

import com.shv.vknewsclient.domain.FeedPost
import com.shv.vknewsclient.domain.PostComment

sealed class HomeScreenState {

    object Initial : HomeScreenState()

    data class Posts(val posts: List<FeedPost>) : HomeScreenState()
    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>) : HomeScreenState()
}