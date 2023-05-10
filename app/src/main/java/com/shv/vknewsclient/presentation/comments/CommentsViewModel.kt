package com.shv.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shv.vknewsclient.data.repository.NewsFeedRepositoryImpl
import com.shv.vknewsclient.domain.entity.FeedPost
import com.shv.vknewsclient.domain.usecase.GetCommentsUseCase
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)
    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}