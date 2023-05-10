package com.shv.vknewsclient.data.mapper

import android.util.Log
import com.shv.vknewsclient.data.model.CommentsResponseDto
import com.shv.vknewsclient.data.model.NewsFeedResponseDto
import com.shv.vknewsclient.domain.entity.FeedPost
import com.shv.vknewsclient.domain.entity.PostComment
import com.shv.vknewsclient.domain.entity.StatisticItem
import com.shv.vknewsclient.domain.entity.StatisticType
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        Log.d("NewsFeedMapper", "posts.size: ${posts.size}")
        Log.d("NewsFeedMapper", "groups.size: ${groups.size}")

        for (post in posts) {
            if (post.id == 0L) continue
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: continue
            val feedPost = FeedPost(
                id = post.id,
                communityId = post.communityId,
                communityName = group.name,
                publicationDate = mapTimestampToDate(post.date),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.VIEWS, count = post.views.count),
                    StatisticItem(type = StatisticType.SHARES, count = post.reposts.count),
                    StatisticItem(type = StatisticType.COMMENTS, count = post.comments.count),
                    StatisticItem(type = StatisticType.LIKES, count = post.likes.count)
                ),
                isLiked = post.likes.isUserLikes > 0
            )
            result.add(feedPost)
        }
        return result
    }

    fun mapPostCommentsDtoToPostComments(response: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()

        val comments = response.content.comments
        val profiles = response.content.profiles

        Log.d("PostCommentsMapper", "comments.size: ${comments.size}")
        Log.d("PostCommentsMapper", "profiles.size: ${profiles.size}")

        for (comment in comments) {
            if (comment.text.isBlank()) continue
            val author = profiles.find { it.id == comment.authorId } ?: continue
            val postComment = PostComment(
                id = comment.id,
                authorName = String.format("%s %s", author.firstName, author.lastName),
                authorAvatarUrl = author.photoUrl,
                commentText = comment.text,
                publicationDate = mapTimestampToDate(comment.date)
            )

            result.add(postComment)
        }
        return result
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        return SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault()).format(date)
    }
}