package com.shv.vknewsclient.domain.usecase

import com.shv.vknewsclient.domain.entity.FeedPost
import com.shv.vknewsclient.domain.repository.NewsFeedRepository

class IgnorePostUseCase(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.ignorePost(feedPost)
    }
}