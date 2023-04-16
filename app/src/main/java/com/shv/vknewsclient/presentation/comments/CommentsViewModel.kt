package com.shv.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shv.vknewsclient.data.repository.CommentsRepository
import com.shv.vknewsclient.domain.FeedPost
import kotlinx.coroutines.launch

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState>
        get() = _screenState

    private val repository = CommentsRepository(application)

    init {
        _screenState.value = CommentsScreenState.Loading
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        viewModelScope.launch {
            val comments = repository.loadComments(feedPost)
            if (comments.isNotEmpty()) {
                _screenState.value = CommentsScreenState.Comments(
                    feedPost = feedPost,
                    comments = comments
                )
            } else {
                _screenState.value = CommentsScreenState.NoComments
            }
        }
    }
}