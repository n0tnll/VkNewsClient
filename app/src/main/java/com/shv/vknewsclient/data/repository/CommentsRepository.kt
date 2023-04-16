package com.shv.vknewsclient.data.repository

import android.app.Application
import android.util.Log
import com.shv.vknewsclient.data.mapper.PostCommentsMapper
import com.shv.vknewsclient.data.network.ApiFactory
import com.shv.vknewsclient.domain.FeedPost
import com.shv.vknewsclient.domain.PostComment
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken

class CommentsRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)

    private val apiService = ApiFactory.apiService
    private val mapper = PostCommentsMapper()

    private val _postComments = mutableListOf<PostComment>()
    val postComments: List<PostComment>
        get() = _postComments.toList()

    suspend fun loadComments(feedPost: FeedPost): List<PostComment> {
        val response = apiService.getPostComments(
            token = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        Log.d("loadComments", response.toString())
        val comments = mapper.mapPostCommentsDtoToPostComments(response)
        _postComments.addAll(comments)
        return postComments
    }

    private fun getAccessToken(): String =
        token?.accessToken ?: throw IllegalStateException("Token is null")
}