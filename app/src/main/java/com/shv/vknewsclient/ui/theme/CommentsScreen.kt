package com.shv.vknewsclient.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shv.vknewsclient.CommentsViewModel
import com.shv.vknewsclient.CommentsViewModelFactory
import com.shv.vknewsclient.domain.FeedPost
import com.shv.vknewsclient.domain.PostComment


@Composable
fun CommentsScreen(
    feedPost: FeedPost,
    onBackPressed: () -> Unit
) {
    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(feedPost)
    )
    val screenState = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    val currentState = screenState.value

    if (currentState is CommentsScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Comments for FeedPost id: ${currentState.feedPost.id}") },
                    backgroundColor = MaterialTheme.colors.primary,
                    navigationIcon = {
                        IconButton(onClick = { onBackPressed() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 60.dp
                )
            ) {
                items(
                    items = currentState.comments,
                    key = { it.id }
                ) { comment ->
                    CommentItem(comment)
                }
            }
        }
    }
}

@Composable
private fun CommentItem(
    comment: PostComment
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        Row() {
            Image(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .size(40.dp),
                painter = painterResource(id = comment.authorAvatarResId),
                contentDescription = null
            )
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = "${comment.authorName} CommentID:${comment.id}",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = comment.commentText,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = comment.publicationDate,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
}