package com.shv.vknewsclient.ui.theme

import com.shv.vknewsclient.domain.FeedPost
import com.shv.vknewsclient.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}