package com.shv.vknewsclient.data.mapper

import android.util.Log
import com.shv.vknewsclient.data.model.CommentsResponseDto
import com.shv.vknewsclient.domain.PostComment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostCommentsMapper {

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