package com.shv.vknewsclient.domain.usecase

import com.shv.vknewsclient.domain.entity.FeedPost
import com.shv.vknewsclient.domain.repository.NewsFeedRepository
import javax.inject.Inject

class IgnorePostUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.ignorePost(feedPost)
    }
}