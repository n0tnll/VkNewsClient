package com.shv.vknewsclient.presentation.comments

import com.shv.vknewsclient.domain.FeedPost
import com.shv.vknewsclient.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    object NoComments: CommentsScreenState()
    object Loading: CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}