package com.shv.vknewsclient.data.mapper

import android.util.Log
import com.shv.vknewsclient.data.model.NewsFeedResponseDto
import com.shv.vknewsclient.domain.FeedPost
import com.shv.vknewsclient.domain.StatisticItem
import com.shv.vknewsclient.domain.StatisticType
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        Log.d("mapResponseToPosts", "val posts = responseDto.newsFeedContent.posts: SIZE: ${posts.size}")
        Log.d("mapResponseToPosts", "val groups = responseDto.newsFeedContent.groups SIZE: ${groups.size}")

        for (post in posts) {
            if (post.id == null) continue
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: continue
            Log.d("mapResponseToPosts", "В цикле. Post source id: ${post.text} | Group id: ${group.name}")
            val feedPost = FeedPost(
                id = post.id,
                communityName = group.name,
                publicationDate = post.date.toString(),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.VIEWS, count = post.views.count),
                    StatisticItem(type = StatisticType.SHARES, count = post.reposts.count),
                    StatisticItem(type = StatisticType.COMMENTS, count = post.comments.count),
                    StatisticItem(type = StatisticType.LIKES, count = post.likes.count)
                )
            )
            result.add(feedPost)
        }
        Log.d("mapResponseToPosts", "list feedPost.size: ${result.size}")
        return result
    }
}