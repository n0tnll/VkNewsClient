package com.shv.vknewsclient.presentation.news

import com.shv.vknewsclient.domain.entity.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    object Loading : NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>, val nextDataIsLoading: Boolean = false) :
        NewsFeedScreenState()
}