package com.shv.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shv.vknewsclient.data.repository.CommentsRepository
import com.shv.vknewsclient.domain.FeedPost
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = CommentsRepository(application)

    val screenState = repository.loadComments(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}