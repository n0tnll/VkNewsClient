package com.shv.vknewsclient.domain.repository

import com.shv.vknewsclient.domain.entity.AuthState
import com.shv.vknewsclient.domain.entity.FeedPost
import com.shv.vknewsclient.domain.entity.PostComment
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getRecommendations(): StateFlow<List<FeedPost>>

    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun ignorePost(feedPost: FeedPost)

    suspend fun changeLikeStatus(feedPost: FeedPost)
}