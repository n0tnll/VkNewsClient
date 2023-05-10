package com.shv.vknewsclient.presentation.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shv.vknewsclient.data.repository.NewsFeedRepositoryImpl
import com.shv.vknewsclient.domain.entity.FeedPost
import com.shv.vknewsclient.domain.usecase.ChangeLikeStatusUseCase
import com.shv.vknewsclient.domain.usecase.GetRecommendationUseCase
import com.shv.vknewsclient.domain.usecase.IgnorePostUseCase
import com.shv.vknewsclient.domain.usecase.LoadNextDataUseCase
import com.shv.vknewsclient.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        Log.d("NewsFeedViewModel", "Exception caught by ExceptionHandler")
    }

    private val repository = NewsFeedRepositoryImpl(application)
    private val loadNextDataEvents = MutableSharedFlow<Unit>()

    private val getRecommendationUseCase = GetRecommendationUseCase(repository)
    private val loadNextDataUseCase = LoadNextDataUseCase(repository)
    private val ignorePostUseCase = IgnorePostUseCase(repository)
    private val changeLikeStatusUseCase = ChangeLikeStatusUseCase(repository)

    private val recommendationFlow = getRecommendationUseCase()

    private val loadNextDataFlow =  flow {
        loadNextDataEvents.collect {
            emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationFlow.value,
                    nextDataIsLoading = true
                )
            )
        }
    }

    val screenState = recommendationFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(posts = it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(loadNextDataFlow)

    fun loadNextRecommendations() {
        viewModelScope.launch {
            loadNextDataEvents.emit(Unit)
            loadNextDataUseCase()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost)

        }
    }

    fun ignorePost(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            ignorePostUseCase(feedPost)
        }
    }
}