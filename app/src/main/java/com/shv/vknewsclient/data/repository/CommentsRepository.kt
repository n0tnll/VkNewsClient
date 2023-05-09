package com.shv.vknewsclient.data.repository

import android.app.Application
import com.shv.vknewsclient.data.mapper.PostCommentsMapper
import com.shv.vknewsclient.data.network.ApiFactory
import com.shv.vknewsclient.domain.FeedPost
import com.shv.vknewsclient.domain.PostComment
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry

class CommentsRepository(application: Application) {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)

    private val apiService = ApiFactory.apiService
    private val mapper = PostCommentsMapper()

    private val _postComments = mutableListOf<PostComment>()
    val postComments: List<PostComment>
        get() = _postComments.toList()

    fun loadComments(feedPost: FeedPost): Flow<List<PostComment>> = flow {
        val response = apiService.getPostComments(
            token = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        val comments = mapper.mapPostCommentsDtoToPostComments(response)
        _postComments.addAll(comments)
        emit(postComments)
    }.retry {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }

    private fun getAccessToken(): String =
        token?.accessToken ?: throw IllegalStateException("Token is null")

    companion object {
        private const val RETRY_TIMEOUT_MILLIS = 3000L
    }
}